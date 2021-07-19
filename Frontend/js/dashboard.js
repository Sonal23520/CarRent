//////////////////Fetch Reg Detail///////////////////
$.ajax({
  type: "get",
  url: "http://localhost:8080/Backend/customer",
  success: function (response) {
    $("#customerCount").text(response.data.length);
  },
});

$.ajax({
  type: "get",
  url: "http://localhost:8080/Backend/driver",
  success: function (response) {
    $("#driverCount").text(response.data.length);
  },
});

$.ajax({
  type: "get",
  url: "http://localhost:8080/Backend/car",
  success: function (response) {
    $("#carCount").text(response.data.length);
  },
});

///////////////////Request Load//////////////////
$.ajax({
  type: "get",
  url: "http://localhost:8080/Backend/request",
  success: function (response) {
    response.data.forEach((element) => {
      $("#requestData").append(
        `
        <tr>
        <td>${element.reqid}</td>
        <td>${element.customer.email}</td>
        <td>${element.car.id}</td>
        <td>${element.schedule.id}</td>
        <td class="text-danger">${element.status}</td>
        <td>
          <button class="btn btn-success " onclick="actionbtn(this)">Accept</button>
        </td>
      </tr>`
      );
    });
  },
});

function actionbtn(result) {
  $($(result).parent()).prev().text("ACTIVE");
  $($(result).parent()).prev().removeClass("text-danger");
  $($(result).parent()).prev().addClass("text-success");
}
