let token = document.getElementById('token').value;


$(document).ready(function () {
  getTable();
});

$(document).on('click', '#editUserBtn', function () {
  $("#updateUserId").val($(this).closest("tr").find("#tableId").text()).prop("disabled", true);
  $("#updateUserName").val($(this).closest("tr").find("#tableUsername").text());
  $("#updateFirstName").val($(this).closest("tr").find("#tableFirstName").text());
  $("#updateLastName").val($(this).closest("tr").find("#tableLastName").text());
  $("#updateUserPass").val($(this).closest("tr").find("#tablePassword").text());
  $("#updateUserEmail").val($(this).closest("tr").find("#tableEmail").text());

  let role = $(this).closest("tr").find("#tableRole").text();

  if (role === "ROLE_ADMIN") {
    $('#updateUserRole option:contains("ADMIN")').prop("selected", true);
  } else {
    $('#updateUserRole option:contains("USER")').prop("selected", true);
  }
});


//addForm
$("#addFormUser").click(function (event) {
  event.preventDefault();
  addForm();
  $('#addUserName').val('');
  $('#addFirstName').val('');
  $('#addLastName').val('');
  $('#addPassword').val('');
  $('#addMail').val('');
});

$("#resetTable").click(function () {
  getTable();
});

function addForm() {

  let user = {
    'username': $("#addUserName").val(),
    'firstName': $("#addFirstName").val(),
    'lastName': $("#addLastName").val(),
    'password': $("#addPassword").val(),
    'email': $("#addMail").val(),
    'roles': $("#addRoleUser").val() + $("#addRoleAdmin").val()
  };

  $.ajax({

    type: 'POST',
    crossDomain: true,
    url: "http://localhost:8383/restapi/v1/admin/user/",
    contentType: 'application/json;',
    data: JSON.stringify(user),
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization':token
    },
    async: true,
    dataType: 'JSON',
    success: function () {
      getTable()
    }
  });
}

//updateForm
$("#updateFormUser").click(function (event) {
  event.preventDefault();
  updateForm();
  $("#editUser").modal('toggle');
  getTable();
});

function updateForm() {
  let user = {
    'id': $("#updateUserId").val(),
    'username': $("#updateUserName").val(),
    'firstName': $("#updateFirstName").val(),
    'lastName': $("#updateLastName").val(),
    'password': $("#updateUserPass").val(),
    'email': $("#updateUserEmail").val(),
    'roles': $("#updateUserRole").val()
  };

  $.ajax({

    type: 'Put',
    crossDomain: true,
    url: "http://localhost:8383/restapi/v1/admin/user/",

    contentType: 'application/json;',
    data: JSON.stringify(user),
    headers:{
    'Authorization':token},


    success: function () {
      getTable();
    }
  });
}

//deleteForm
$(document).on('click', '#deleteUser', function () {
  deleteUserId = $(this).closest("tr").find("#tableId").text();
  deleteUser(deleteUserId);
});

function deleteUser(id) {

  $.ajax({
    type: 'DELETE',
    crossDomain: true,
    url: "http://localhost:8383/restapi/v1/admin/user/" + id,

    contentType: 'application/json;',
    data: JSON.stringify(id),
    headers:{
      'Authorization':token },

    success: function () {
      getTable();
    }
  });
}


function getTable() {
  $.ajax({
    type: 'GET',
    crossDomain: true,
    url: "http://localhost:8383/restapi/v1/admin/",
    contentType: 'application/json;',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization':token
    },
    dataType: 'JSON',

    success: function (listUsers) {
      let htmlTable = "";
      for (let i = 0; i < listUsers.length; i++) {

        htmlTable += `<tr id="list" valign="center">
                        <td id="tableId" align="center">${listUsers[i].id}</td>
                        <td id="tableUsername"  align="center">${listUsers[i].username}</td>
                        <td id="tableFirstName"  align="center">${listUsers[i].firstName}</td>
                        <td id="tableLastName" align="center">${listUsers[i].lastName}</td>
                        <td id="tableEmail" align="center">${listUsers[i].email}</td>
                        <td id="tableRole" align="center">${listUsers[i].roles}</td>
                        <td align="center">
                            <button id="editUserBtn"  class="btn btn-primary" type="button" data-toggle="modal" data-target="#editUser">Редактировать</button>
                            <button id="deleteUser" class="btn btn-danger" type="button" data-target="#deleteUser"> Удалить!</button>
                        </td>
                        </tr>`;
      }
      $("#tableUser #list").remove();
      $("#tableUser thead").after(htmlTable);
    }
  })
}
