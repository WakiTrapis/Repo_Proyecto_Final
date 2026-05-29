document.addEventListener("DOMContentLoaded", function() {
    const selectTatuaje = document.getElementById('tatuaje') || document.getElementById('selectTatuaje') || document.querySelector('select[name="tatuaje"]');
    const selectArtista = document.getElementById('artista') || document.getElementById('selectArtista') || document.querySelector('select[name="artista"]');
    const inputHoras = document.getElementById('duracion') || document.getElementById('inputHoras') || document.querySelector('input[name="duracion"]');
    const inputPrecio = document.getElementById('precioSesion') || document.getElementById('inputPrecio') || document.querySelector('input[name="precioSesion"]');

    function calcularPrecioCita() {
        if (!selectTatuaje || !selectArtista || !inputHoras || !inputPrecio) {
            return;
        }

        const optTatuaje = selectTatuaje.options[selectTatuaje.selectedIndex];
        const precioTatuaje = optTatuaje ? parseFloat(optTatuaje.getAttribute('data-precio')) : 0;
        let numSesiones = optTatuaje ? parseInt(optTatuaje.getAttribute('data-sesiones')) : 1;
        if (isNaN(numSesiones) || numSesiones <= 0) numSesiones = 1;

        const optArtista = selectArtista.options[selectArtista.selectedIndex];
        const precioHoraArtista = optArtista ? parseFloat(optArtista.getAttribute('data-preciohora')) : 0;

        const horasCita = parseFloat(inputHoras.value) || 0;


        if (precioTatuaje > 0 || precioHoraArtista > 0) {
            const costeBaseTatuajePorSesion = precioTatuaje / numSesiones;
            const costeTiempoArtista = precioHoraArtista * horasCita;
            
            const precioFinal = costeBaseTatuajePorSesion + costeTiempoArtista;
            
            inputPrecio.value = precioFinal.toFixed(2);
        } else {
            inputPrecio.value = "0.00";
        }
    }

    if (selectTatuaje) selectTatuaje.addEventListener('change', calcularPrecioCita);
    if (selectArtista) selectArtista.addEventListener('change', calcularPrecioCita);
    if (inputHoras) {
        inputHoras.addEventListener('input', calcularPrecioCita);
        inputHoras.addEventListener('change', calcularPrecioCita);
    }

    const idInput = document.getElementById('id') || document.querySelector('input[name="id"][type="hidden"]');
    const esEdicion = idInput && idInput.value && idInput.value.trim() !== '';
    if (!esEdicion) {
        calcularPrecioCita();
    }
});