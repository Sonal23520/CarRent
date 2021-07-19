function loadTable() {
  $.ajax({
    type: "get",
    url: "http://localhost:8080/Backend/customer",
    success: function (response) {
      $("#tableData").empty();
      response.data.forEach((element) => {
        $("#tableData").append(
          `<tr><td>${element.email}</td><td>${element.password}</td>
            <td>${element.address}</td><td>${element.nic}</td><td>${element.license}</td>
            <td>${element.tel}</td></tr>`
        );
      });
    },
  });
}
loadTable();
//////////////////////////
if (sessionStorage.getItem("result") == "add") {
  $("#regBtn").text("Add User");
  $("#regBtn").addClass("btn-primary");
} else if (sessionStorage.getItem("result") == "update") {
  $("#regBtn").text("Update User");
  $("#regBtn").addClass("btn-success");
} else if (sessionStorage.getItem("result") == "delete") {
  $("#regBtn").text("Delete User");
  $("#regBtn").addClass("btn-danger");
}
////////////////////////////////////
$("#license_files").change(function () {
  filename = this.files[0].name;
  $("#license_lable").text(filename);
});

$("#nic_files").change(function () {
  filename = this.files[0].name;
  $("#nic_lable").text(filename);
});

/////////////ADD USER//////////////
function getFormData($form) {
  var unindexed_array = $form.serializeArray();
  var indexed_array = {};
  $.map(unindexed_array, function (n, i) {
    indexed_array[n["name"]] = n["value"];
  });
  return indexed_array;
}
function customerDetailReg() {
  $.ajax({
    url: "http://localhost:8080/Backend/customer",
    method: "POST",
    dataType: "json",
    contentType: "application/json",
    data: JSON.stringify(getFormData($("#userRegData"))),
    success: function () {
      loadTable();
      $("#userRegData").trigger("reset");
      document.getElementById("nic_files").value = "";
      document.getElementById("license_files").value = "";
      $("#license_lable").text("Upload License");
      $("#nic_lable").text("Upload NIC");
    },
  });
}
function customerReg() {
  let nicObject = $("#nic_files")[0].files[0];
  let nic_fileName = $("#nic_files")[0].files[0].name;
  let licenseObject = $("#license_files")[0].files[0];
  let license_fileName = $("#license_files")[0].files[0].name;
  let data = new FormData();
  data.append("nic", nicObject, nic_fileName);
  data.append("license", licenseObject, license_fileName);
  $.ajax({
    url: "http://localhost:8080/Backend/customer",
    method: "post",
    async: true,
    processData: false,
    contentType: false,
    data: data,
    success: function (data) {
      if (data == true) {
        customerDetailReg();
      }
    },
  });
}
function addUser() {
  if ($("#email").val().length == 0) {
    $("#passwordmatcherro").css("display", "block");
    $("#passwordmatcherro").text("please fill email");
  } else if ($("#nicnumber").val().length == 0) {
    $("#passwordmatcherro").css("display", "block");
    $("#passwordmatcherro").text("please fill nic number");
  } else if ($("#nic_files").val().length == 0) {
    $("#passwordmatcherro").css("display", "block");
    $("#passwordmatcherro").text("please upload nic ");
  } else if ($("#licensenumber").val().length == 0) {
    $("#passwordmatcherro").css("display", "block");
    $("#passwordmatcherro").text("please fill license number");
  } else if ($("#license_files").val().length == 0) {
    $("#passwordmatcherro").css("display", "block");
    $("#passwordmatcherro").text("please upload license");
  } else if ($("#address").val().length == 0) {
    $("#passwordmatcherro").css("display", "block");
    $("#passwordmatcherro").text("please fill address");
  } else if ($("#contact").val().length == 0) {
    $("#passwordmatcherro").css("display", "block");
    $("#passwordmatcherro").text("please fill contact");
  } else {
    $.ajax({
      url: `http://localhost:8080/Backend/login/${$("#email").val()}`,
      method: "GET",
      success: function (data) {
        if (data.data == false) {
          $("#passwordmatcherro").css("display", "none");
          ////////////////////////////////////////////////
          if ($("#newpassword").val() === $("#confirmpassword").val()) {
            $("#passwordmatcherro").css("display", "none");
            customerReg();
          } else {
            $("#passwordmatcherro").text("password doesn't match");
            $("#passwordmatcherro").css("display", "block");
          }
          ////////////////////////////////////////////////
        } else {
          $("#passwordmatcherro").css("display", "block");
          $("#passwordmatcherro").text("email already exist");
        }
      },
    });
  }
}
/////////////////////////////////
$("#regBtn").click(function () {
  if ($("#regBtn").text() == "Add User") {
    addUser();
  }
});
