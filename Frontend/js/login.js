const toggleForm = () => {
  const container = document.querySelector(".container");
  container.classList.toggle("active");
};

////////////////////////////////////////////////////////////////

$("#loginbtn").click(function () {
  let data = $("#logindata").serializeArray();

  $.ajax({
    url: `http://localhost:8080/Backend/login/admin/${data[0].value}/${data[1].value}`,
    method: "GET",
    success: function (data) {
      dashboardload(data);
    },
  });
});

function dashboardload(result) {
  if (result.data == "ADMINDETAILRIGHT") {
    window.location.replace("../page/admin.html");
  }

  if (result.data == "ADMINDETAILWRONG") {
    $("#erromsg").text("incorrect password");
    $("#erromsg").css("display", "block");
  }

  if (result.data == "ADMINNOTFOUND") {
    customerlogin();
  }
}

function customerlogin() {
  let data = $("#logindata").serializeArray();

  $.ajax({
    url: `http://localhost:8080/Backend/login/${data[0].value}/${data[1].value}`,
    method: "GET",
    success: function (data) {
      pageload(data);
    },
  });
}

function pageload(result) {
  if (result.data == "CUSTOMERDETAILRIGHT") {
    sessionStorage.setItem("result", "success");
    window.location.replace("../index.html");
  }

  if (result.data == "CUSTOMERDETAILWRONG") {
    $("#erromsg").text("incorrect password");
    $("#erromsg").css("display", "block");
  }

  if (result.data == "CUSTOMERNOTFOUND") {
    $("#erromsg").text("user not found");
    $("#erromsg").css("display", "block");
  }
}

////////////////////////////////////////////////////////////////

$("#license_files").change(function () {
  filename = this.files[0].name;
  $("#license_lable").text(filename);
});

$("#nic_files").change(function () {
  filename = this.files[0].name;
  $("#nic_lable").text(filename);
});

/////////////////////Registration////////////////////////////////////////////

function getFormData($form) {
  var unindexed_array = $form.serializeArray();
  var indexed_array = {};
  $.map(unindexed_array, function (n, i) {
    indexed_array[n["name"]] = n["value"];
  });
  return indexed_array;
}
/////////////////////////////////////////////////////////////////
function customerDetailReg() {
  $.ajax({
    url: "http://localhost:8080/Backend/customer",
    method: "POST",
    dataType: "json",
    contentType: "application/json",
    data: JSON.stringify(getFormData($("#signupdata"))),
    success: function () {
      sessionStorage.setItem("result", "success");
      window.location.replace("../index.html");
    },
  });
}
/////////////////////////////////////////////////////////////////
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
/////////////////////////////////////////////////////////////////
$("#signup").click(function () {
  if ($("#signupemail").val().length == 0) {
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
      url: `http://localhost:8080/Backend/login/${$("#signupemail").val()}`,
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
});
