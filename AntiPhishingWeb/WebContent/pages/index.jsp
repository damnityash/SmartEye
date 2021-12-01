<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
<head>
<jsp:include page="../tiles/header.jsp"></jsp:include>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="mt-4">
				<h1 class="text-center text-dark">Phishing URL Detect</h1>

				<div class="offset-md-2	col-md-8">
					<div class="card">
						<!-- 						<div class="card-header bg-dark text-white">Phishing URL -->
						<!-- 							Detection</div> -->
						<div class="card-body">
							<form name="form_url" id="form_url"
								action="javascript:fnSubmit();">


								<div class="mb-4">
									<label for="exampleInputURL1" class="form-label">URL
										address</label> <input type="url" class="form-control" id="url"
										name="url" aria-describedby="URL" pattern="https?://.+"
										required placeholder="https://example.com" /> <span
										class="badge bg-info text-white" for="url">Enter an
										https:// URL </span>
								</div>
								<div class="mb-3">
									<h4 class="text-info">
										Result : <span class="text-capitalize" id="result"> <span>
									</h4>
								</div>
								<div id="loaderss" class="row mx-auto  justify-content-center">
									<div class="spinner-grow col-2 ml-1 text-primary" role="status">
										<span class="visually-hidden">Loading...</span>
									</div>
									<div class="spinner-grow col-2 ml-1 text-secondary" role="status">
										<span class="visually-hidden">Loading...</span>
									</div>
									<div class="spinner-grow col-2 ml-1 text-success" role="status">
										<span class="visually-hidden">Loading...</span>
									</div>
									<div class="spinner-grow ml-1 text-danger" role="status">
										<span class="visually-hidden">Loading...</span>
									</div>
									<div class="spinner-grow ml-1 text-warning" role="status">
										<span class="visually-hidden">Loading...</span>
									</div>
									<div class="spinner-grow ml-1 text-info" role="status">
										<span class="visually-hidden">Loading...</span>
									</div>
									<div class="spinner-grow ml-1 text-dark" role="status">
										<span class="visually-hidden">Loading...</span>
									</div>
								</div>
								<div id="btns" class="gap-2 col-6 mx-auto">
									<button type="submit" class="btn btn-outline-dark w-100 me-2">Is
										it phishing?</button>
								</div>

							</form>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
	<jsp:include page="../tiles/footer.jsp"></jsp:include>



</body>
<script type="text/javascript">
$(document).ready(function(){
	$("#loaderss").hide();
});

function fnSubmit(){	
	$("#btns").hide();
	$("#loaderss").show();
	var str = $("#form_url").serialize();

	$.post("<%=request.getContextPath()%>/connector/ajax.jsp?methodId=checkURL",
						str, function(data) {

							data = $.trim(data);

							$("#result").text(data);
							$('#form_url')[0].reset();

							$("#btns").show();
							$("#loaderss").hide();
						});

	}
</script>
</html>