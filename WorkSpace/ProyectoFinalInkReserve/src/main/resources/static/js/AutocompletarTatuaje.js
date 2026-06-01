// Script para autocompletar cliente y artista al seleccionar un tatuaje en el formulario de citas
document.addEventListener("DOMContentLoaded", function () {
		 	    const modalCitas = document.getElementById('ModalFormCitas');
		 	    if (modalCitas) {
		 	        modalCitas.addEventListener('shown.bs.modal', function () {
		 	            const selectTatuaje = document.getElementById("selectTatuaje");
		 	            const selectCliente = document.getElementById("selectCliente");
		 	            const selectArtista = document.getElementById("selectArtista");
	
		 	            if (selectTatuaje) {
		 	                selectTatuaje.addEventListener("change", function () {
		 	                    const opcionSeleccionada = this.options[this.selectedIndex];
		 	                    const idClienteAsociado = opcionSeleccionada.getAttribute("data-cliente");
		 	                    const idArtistaAsociado = opcionSeleccionada.getAttribute("data-artista");
		 	                    
		 	                    if (selectCliente && idClienteAsociado) {
		 	                        selectCliente.value = idClienteAsociado;
		 	                    } else if (selectCliente) {
		 	                        selectCliente.value = "";
		 	                    }
		 	                    
		 	                    if (selectArtista && idArtistaAsociado) {
		 	                        selectArtista.value = idArtistaAsociado;
		 	                    } else if (selectArtista) {
		 	                        selectArtista.value = "";
		 	                    }
		 	                });
		 	            }
		 	        });
		 	    }
		 	});