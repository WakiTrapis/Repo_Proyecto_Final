const DIAS = ['Lun', 'Mar', 'Mié', 'Jue', 'Vie', 'Sáb', 'Dom'];
	    let citasArtista = [];
	    let inicioSemana;

	    function getLunes(fecha) {
	        const d = new Date(fecha);
	        const dia = d.getDay();
			const diff = (dia === 0) ? -6 : 1 - dia;
			d.setDate(d.getDate() + diff);
			d.setHours(0, 0, 0, 0);
	        return d;
	    }

	    function formatFecha(fecha) {
			const anio = fecha.getFullYear();
			    const mes = String(fecha.getMonth() + 1).padStart(2, '0');
			    const dia = String(fecha.getDate()).padStart(2, '0');
			    return `${anio}-${mes}-${dia}`;
	    }

	    function formatHora(isoStr) {
	        return isoStr.substring(11, 16);
	    }

	    function cargarCitas(artistaId) {
	        fetch(`/api/artistas/citas/${artistaId}`)
	            .then(res => res.json())
	            .then(data => {
	                citasArtista = data;
	                renderSemana();
	            })
	            .catch(err => console.error('Error cargando citas:', err));
	    }

	    function renderSemana() {
	        const cabecera = document.getElementById('cabeceraSemana');
	        const cuerpo = document.getElementById('cuerpoSemana');
	        const labelSemana = document.getElementById('labelSemana');
	        const hoy = formatFecha(new Date());

	        const dias = [];
	        for (let i = 0; i < 7; i++) {
	            const d = new Date(inicioSemana);
	            d.setDate(d.getDate() + i);
	            dias.push(d);
	        }

	        labelSemana.textContent =
	            dias[0].toLocaleDateString('es-ES', { day: '2-digit', month: 'short' }) +
	            ' — ' +
	            dias[6].toLocaleDateString('es-ES', { day: '2-digit', month: 'short', year: 'numeric' });

	        cabecera.innerHTML = '<tr>' + dias.map((d, i) => {
	            const esHoy = formatFecha(d) === hoy;
	            return `<th class="py-2 ${esHoy ? 'text-success' : 'text-secondary'}">
	                        <div class="small">${DIAS[i]}</div>
	                        <div class="fs-6 fw-bold text-white">${String(d.getDate()).padStart(2, '0')}</div>
	                    </th>`;
	        }).join('') + '</tr>';

	        cuerpo.innerHTML = '<tr>' + dias.map(d => {
	            const fechaDia = formatFecha(d);
	            const esHoy = fechaDia === hoy;
	            const citasDia = citasArtista.filter(c => c.inicio.startsWith(fechaDia));

	            const contenido = citasDia.length === 0
	                ? `<span class="text-secondary fst-italic">Libre</span>`
	                : citasDia.map(c =>
	                    `<div class="rounded-3 p-1 mb-1 text-start bg-success bg-opacity-10 border-start border-success border-3">
	                        <div class="fw-bold text-success" style="font-size:0.7rem;">
	                            ${formatHora(c.inicio)} - ${formatHora(c.fin)}
	                        </div>
	                        <div class="text-white text-truncate" style="font-size:0.7rem;">${c.title}</div>
	                        <div class="text-secondary" style="font-size:0.65rem;">${c.cliente}</div>
	                    </div>`
	                ).join('');

	            return `<td class="p-1 align-top ${esHoy ? 'table-success bg-opacity-10' : ''}">
	                        ${contenido}
	                    </td>`;
	        }).join('') + '</tr>';
	    }
	    document.getElementById('btnSemanaAnterior').addEventListener('click', function () {
	        inicioSemana.setDate(inicioSemana.getDate() - 7);
	        renderSemana();
	    });

	    document.getElementById('btnSemanaSiguiente').addEventListener('click', function () {
	        inicioSemana.setDate(inicioSemana.getDate() + 7);
	        renderSemana();
	    });

	    const modalPerfil = document.getElementById('ModalPerfilArtistas');
	    if (modalPerfil) {
	        modalPerfil.addEventListener('shown.bs.modal', function () {
	            const artistaId = modalPerfil.querySelector('[data-artista-id]')?.dataset?.artistaId;
	            console.log('artistaId:', artistaId);
	            if (artistaId) {
	                inicioSemana = getLunes(new Date());
	                cargarCitas(artistaId);
	            }
	        });
	    }