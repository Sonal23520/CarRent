$(".filters ul li").click(function () {
  $(".filters ul li").removeClass("active");
  $(this).addClass("active");

  var data = $(this).attr("data-filter");
  $grid.isotope({
    filter: data,
  });
});

var $grid = $(".grid").isotope({
  itemSelector: ".all",
  percentPosition: true,
  masonry: {
    columnWidth: ".all",
  },
});
//////////////////////////////////
$(window).scroll(function () {
  // 100 = The point you would like to fade the nav in.

  if ($(window).scrollTop() > 100) {
    $(".navbar").addClass("show");
  } else {
    $(".navbar").removeClass("show");
  }
});
//////////////////////////////////
function carmodalswitch(name) {
  switch (name) {
    case "Suzuki_Alto":
      $("#Suzuki_Alto_modal").modal("show");
      break;
    case "Toyota_Corolla":
      console.log("no");
      break;

    default:
      break;
  }
}

$(".all").click(function () {
  carmodalswitch($(this).attr("id"));
});

//////////////////////////////
$("#login_link").click(function () {
  if ($(this).text() == "Login") {
    window.location.replace("../page/login.html");
  }
  if ($(this).text() == "Logout") {
    $(this).text("Login");
    $("#login_link").removeClass("logout_button");
  }
});
////////////////////////////////////////

if (sessionStorage.getItem("result") == "success") {
  $("#login_link").text("Logout");
  $("#login_link").addClass("logout_button");
}
//////////////////////////////////////////
$("#booking").click(function () {
  if ($("#login_link").text() == "Login") {
    window.location.replace("../page/login.html");
  }
});
