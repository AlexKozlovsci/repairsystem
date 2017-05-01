
$( document ).ready(function(){


	var add;
	$("#add_new_project").click(function(){
		add = $("#add_new_project").detach();
		$('#all_projects').append('<form method="post" id="project_add"><div class="col-sm-3 new_project"><div class="panel panel-default" id="project_panel"><div class="panel-heading text-center" style="background-color: #3333CC;"><input type="hidden" value="add" name="add" /><input type="text" placeholder="Project name" class="form-control" id="project_name" name="project_name" /></div><div class="panel-body text-center"><p><b>Status: </b><select class="form-control" name="importance"><option value="1">Low importance</option><option value="2">Medium importance</option><option value="3">High importance</option></select></p><input type="submit" class="btn btn-success" id="submit_project" value="Add" /><a href="javascript://" class="btn btn-danger" id="delete_project">Delete</a></div></div></div>');
	});
	$(document).on('click', '#delete_project', function(){
		$('.new_project').remove();
		add.appendTo('#head_name');

	});

	$("#submit_project").click(function(){ 
		AjaxFormRequest('project_add', 'projects.php');
		 return false; 
	});
	

});
function AjaxFormRequest(form_id,url) {
    jQuery.ajax({
        url:     url, //Адрес подгружаемой страницы
        type:     "POST", //Тип запроса
        dataType: "html", //Тип данных
        data: jQuery("#"+form_id).serialize()
 	});
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
