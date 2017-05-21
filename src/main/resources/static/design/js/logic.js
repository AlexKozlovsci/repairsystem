
$( document ).ready(function(){


	/*var add;
	$("#add_new_project").click(function(){
		add = $("#add_new_project").detach();
		$('#all_projects').append('<form method="post" id="project_add"><div class="col-sm-3 new_project"><div class="panel panel-default" id="project_panel"><div class="panel-heading text-center" style="background-color: #3333CC;"><input type="hidden" value="add" name="add" /><input type="text" placeholder="Project name" class="form-control" id="project_name" name="project_name" /></div><div class="panel-body text-center"><p><b>Status: </b><select class="form-control" name="importance"><option value="1">Low importance</option><option value="2">Medium importance</option><option value="3">High importance</option></select></p><input type="submit" class="btn btn-success" id="submit_project" value="Add" /><a href="javascript://" class="btn btn-danger" id="delete_project">Delete</a></div></div></div>');
	});

    var change_status;
    $("#change_status").click(function(){
        //add = $("#changer").detach();
        $('#order_actions').append('<form method="get"  th:action="@{/manager/order{id}/changeOrderStatus(id=workorder.id)}" id="status_change"><div class="col-sm-3 margin_top20"><div class="panel panel-default" id="project_panel"><div class="panel-body text-center"><input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" /><input type="hidden" name="id" th:value="${workorder.id}"/><p><b>Status: </b><select class="form-control" name="status"><option value="1">Open</option><option value="2">In work</option><option value="3">Complete</option><option value="4">Closed</option></select></p><input type="submit" class="btn btn-success" id="submit_status_change" th:value="${workorder.id}" value="Change" /></div></div></div></form>');
    });
	 */

	$(document).on('click', '#delete_project', function(){
		$('.new_project').remove();
		add.appendTo('#head_name');

	});

	/*$("#submit_status_change").click(function(){
		AjaxFormRequest('status_change', '/changeStatus');
		 return false; 
	});
	
*/
});
function AjaxFormRequest(form_id,url) {
    jQuery.ajax({
        url:     url, //Адрес подгружаемой страницы
        type:     "POST", //Тип запроса
        dataType: "html", //Тип данных
        data: jQuery("#"+form_id).serialize()
 	});
}

function showForm(id) {
    document.getElementById(id).style.display = 'block';
}

function hideForm(id) {
    document.getElementById(id).style.display = 'none';
}

function confirmDelete(this_id){
	swal({  
		 	title: "Are you sure?", 
		  	text: "You will not be able to recover this project!",   
		  	type: "warning",   
		  	showCancelButton: true,   
		  	confirmButtonColor: "#DB073D",   
		  	confirmButtonText: "Yes, delete it!",   
		  	closeOnConfirm: false 
		  },
	function(){   
		swal({  
		 	title: "Successful!", 
		  	text: "Discussion deleted successfully!",   
		  	type: "success",   
		  	timer: 3000,
		  	showConfirmButton: false
		  },
		function(){
			$(this_id).submit();
		});
	});
}
