export function formatDate(inputDate: string): string {
    // Convert input string to Date object
    const date: Date = new Date(inputDate);
  
    // Extract date components
    const day: number = date.getDate();
    const month: number = date.getMonth() + 1; // Months are zero-indexed
    const year: number = date.getFullYear();
    const hours: number = date.getHours();
    const minutes: number = date.getMinutes();
  
    // Padding with zero if needed
    const paddedDay: string = day < 10 ? '0' + day : String(day);
    const paddedMonth: string = month < 10 ? '0' + month : String(month);
    const paddedHours: string = hours < 10 ? '0' + hours : String(hours);
    const paddedMinutes: string = minutes < 10 ? '0' + minutes : String(minutes);
  
    // Construct formatted date string
    const formattedDate: string = `${paddedDay}/${paddedMonth}/${year} ${paddedHours}:${paddedMinutes}`;
  
    return formattedDate;
  }
  
  