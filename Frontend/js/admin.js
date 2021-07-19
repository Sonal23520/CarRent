$("#default").empty();
$("#default").load("../page/dashboard.html");
///////////////////////Page Load////////////////////
$("#dashboard").click(function () {
  $("#default").empty();
  $("#default").load("../page/dashboard.html");
});

$("#addCustomer").click(function (e) {
  $("#default").empty();
  sessionStorage.setItem("result", "add");
  $("#default").load("../page/customer.html");
});

$("#updateCustomer").click(function (e) {
  $("#default").empty();
  sessionStorage.setItem("result", "update");
  $("#default").load("../page/customer.html");
});
$("#deleteCustomer").click(function (e) {
  $("#default").empty();
  sessionStorage.setItem("result", "delete");
  $("#default").load("../page/customer.html");
});
