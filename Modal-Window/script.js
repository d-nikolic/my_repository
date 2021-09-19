"use strict";

const modal = document.querySelector(".modal"); //selects class - modal - look into the style.css
const overlay = document.querySelector(".overlay");
const btnCloseModal = document.querySelector(".close-modal");
const btnsOpenModal = document.querySelectorAll(".show-modal");

//function that will make the hidden text appears
const openModal = function () {
  modal.classList.remove("hidden");
  overlay.classList.remove("hidden");
};

//function that will make the text hidden again
const closeModal = function () {
  modal.classList.add("hidden");
  overlay.classList.add("hidden");
};

//if you click on any of the 'Show modal' windows it should display the (initially) hidden text lorem ipsum and also display an overlay blur background
for (let i = 0; i < btnsOpenModal.length; i++) {
  //whenever a click happens on one of these buttons - call this function
  btnsOpenModal[i].addEventListener("click", openModal);
  //click on -X- closes the modal window
  btnCloseModal.addEventListener("click", closeModal);
  //click anywhere around the modal window will also close it
  overlay.addEventListener("click", closeModal);
}

//Esc key on the keyboard also exits the modal window
document.addEventListener("keydown", function (escape) {
  //but only if the modal window is opened - i.e. if it doesn't contain the class 'hidden'
  if (escape.key === "Escape" && !modal.classList.contains("hidden")) {
    closeModal();
  }
});
