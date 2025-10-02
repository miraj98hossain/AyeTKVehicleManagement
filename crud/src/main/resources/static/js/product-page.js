const modal = document.getElementById("productModal");
const btn = document.getElementById("createBtn");
const span = document.getElementsByClassName("close")[0];

btn.onclick = function() { modal.style.display = "block"; }
span.onclick = function() { modal.style.display = "none"; }
window.onclick = function(event) {
    if (event.target === modal) { modal.style.display = "none"; }
}
window.onload = function () {
    const modal = document.getElementById("productModal");
    const idField = document.querySelector('input[name="id"]');

    // If ID exists, it means we are editing → open modal automatically
    if (idField && idField.value) {
        modal.style.display = "block";
    }
};