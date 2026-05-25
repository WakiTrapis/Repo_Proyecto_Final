document.addEventListener("DOMContentLoaded", function() {
    console.log("¡Archivo citas.js cargado correctamente!");
    const selectTatuaje = document.getElementById('tatuaje');
    const selectArtista = document.getElementById('artista');
    const inputHoras = document.getElementById('horas');
    const inputPrecio = document.getElementById('precio');
    function calcularPrecioCita() {
        if (!selectTatuaje || !selectArtista || !inputHoras || !inputPrecio) {
            console.error("Error: No se encuentra alguno de los elementos en el HTML.", {
                tatuaje: !!selectTatuaje,
                artista: !!selectArtista,
                horas: !!inputHoras,
                precio: !!inputPrecio
            });
            return; 
        }
        const optTatuaje = selectTatuaje.options[selectTatuaje.selectedIndex];
        const precioTatuaje = optTatuaje ? parseFloat(optTatuaje.getAttribute('data-precio')) : 0;
        let numSesiones = optTatuaje ? parseInt(optTatuaje.getAttribute('data-sesiones')) : 1;
        if (isNaN(numSesiones) || numSesiones <= 0) numSesiones = 1;


        const optArtista = selectArtista.options[selectArtista.selectedIndex];
        const precioHoraArtista = optArtista ? parseFloat(optArtista.getAttribute('data-preciohora')) : 0;

        const horasCita = parseFloat(inputHoras.value) || 0;

        console.log("Calculando... ", { precioTatuaje, numSesiones, precioHoraArtista, horasCita });

        if (precioTatuaje > 0 || precioHoraArtista > 0) {
            const precioFinal = (precioTatuaje / numSesiones) + (precioHoraArtista * horasCita);
            inputPrecio.value = precioFinal.toFixed(2);
        } else {
            inputPrecio.value = "0.00";
        }
    }

    if (selectTatuaje) selectTatuaje.addEventListener('change', calcularPrecioCita);
    if (selectArtista) selectArtista.addEventListener('change', calcularPrecioCita);
    if (inputHoras)    inputHoras.addEventListener('input', calcularPrecioCita);
});