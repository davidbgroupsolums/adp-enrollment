function calculateFutureDate() {
    // Get the current date and time
    const currentDate = new Date();
  
    // Calculate 4 days from the current date
    const futureDate = new Date();
    futureDate.setDate(currentDate.getDate() + 4);
  
    // Format the dates for display
    const currentDateFormat = currentDate.toDateString();
    const futureDateFormat = `${futureDate.getMonth() + 1}/${futureDate.getDate()}/${futureDate.getFullYear()}`;  
    // Display the results
    console.log("Current Date and Time: " + currentDate);
    console.log("Future Date after 4 days: " + futureDate);
    console.log("Formatted Current Date: " + currentDateFormat);
    console.log("Formatted Future Date: " + futureDateFormat);

    document.getElementById("expiration").textContent = futureDateFormat
  }
  
  // Call the function
  calculateFutureDate();
  