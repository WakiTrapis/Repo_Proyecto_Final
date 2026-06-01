document.addEventListener("DOMContentLoaded", function () {

    document.querySelectorAll("[data-clickable-href]").forEach(el => {
        el.style.cursor = "pointer";
        el.addEventListener("click", function (e) {
            if (e.target.closest('.action-buttons') ||
                e.target.closest('a') ||
                e.target.closest('button') ||
                e.target.closest('i')) {
                return;
            }
            if (document.querySelector('.modal.show')) return;
            window.location.href = this.dataset.clickableHref;
        });
    });

    document.querySelectorAll("[data-auto-open-modal]").forEach(trigger => {
        const modalId = trigger.dataset.autoOpenModal;
        const modalElement = document.getElementById(modalId);
        if (modalElement) {
            const modal = new bootstrap.Modal(modalElement);
            modal.show();
        }
    });


    const selectTatuaje = document.getElementById("selectTatuaje");
    if (selectTatuaje) {
        selectTatuaje.addEventListener("change", function () {
            const opcion = this.options[this.selectedIndex];
            const selectCliente = document.getElementById("selectCliente");
            const selectArtista = document.getElementById("selectArtista");
            if (selectCliente) selectCliente.value = opcion.getAttribute("data-cliente") ?? "";
            if (selectArtista) selectArtista.value = opcion.getAttribute("data-artista") ?? "";
        });
    }
	["ModalFormArtistas", "ModalFormClientes", "ModalFormTatuajes", "ModalFormCitas"].forEach(modalId => {
	    const modal = document.getElementById(modalId);
	    if (!modal) return;
	    modal.addEventListener("hidden.bs.modal", function () {
	        this.querySelectorAll("input, select, textarea").forEach(field => {
	            if (field.name === "id") {
	                field.value = "0";
	            } else if (field.type === "checkbox" || field.type === "radio") {
	                field.checked = false;
	            } else {
	                field.value = "";
	            }
	        });
	    });
	});
});