"use strict";

let secretNumber = Math.floor(Math.random() * 20 + 1);
console.log(secretNumber);
let score = 20;
let highscore = 0;
const displayMessage = function (message) {
  document.querySelector(".message").textContent = message;
};

const checkBtn = document.querySelector(".check");

document.querySelector(".again").addEventListener("click", function () {
  score = 20;
  document.querySelector(".number").textContent = "?";
  document.querySelector(".score").textContent = 20;

  //everything the user enters is a string - empty string === empty guess box(absence of value in the box)
  document.querySelector(".number").style.width = "15rem";
  document.querySelector("body").style.backgroundColor = "#222";
  secretNumber = Math.floor(Math.random() * 20 + 1);
  console.log(secretNumber);
  document.querySelector(".message").textContent = "Start guessing...";

  checkBtn.disabled = false; // can check again
});

document.querySelector(".check").addEventListener("click", function () {
  const guess = Number(document.querySelector(".guess").value);

  //if there is no input
  if (!guess) {
    displayMessage("⛔ NO Number!");

    //if input is wrong
  } else if (guess !== secretNumber) {
    score--;
    if (score === 0) {
      displayMessage("😥 You lost the game!");
      document.querySelector(".score").textContent = score;
      checkBtn.disabled = true;

      return;
    }
    document.querySelector(".score").textContent = score;
    displayMessage(guess > secretNumber ? "⬆ Too high!" : "⬇ Too low!");
  } else {
    document.querySelector(".number").textContent = guess;
    displayMessage("✨🎉Congrats, you guessed the number!");

    document.querySelector("body").style.backgroundColor = "#60b347";
    document.querySelector(".number").style.width = "30rem";
    checkBtn.disabled = true;

    if (score > highscore) {
      highscore = score;
      document.querySelector(".highscore").textContent = highscore;
    }
  }
});
