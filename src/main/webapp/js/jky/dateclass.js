$(function () {
    $("#jqGrid").jqGrid({
        url: '../jky/dateclass/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', width: 50, key: true },
			/*{ label: '所属用户', name: 'userId', width: 80 }, */			
			{ label: '分类名称', name: 'name', width: 80 }, 			
			{ label: '创建时间', name: 'createAt', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
    
    /*$('#datetimepicker').datetimepicker({
    	format: 'yyyy-mm',
    	//todayBtn : "linked", 
        autoclose: true,
        startView: 3,
        minView: 3,
        todayHighlight : true, 
        language: 'zh-CN',
    	pickerPosition:'bottom-left',
    	clearBtn:true,  
    	endDate : $(this).val() ? $(this).val() : new Date()
    }).on('changeDate',function(e){  
    	var beginTime = e.date;
    	$('#datetimepicker').datepicker('setEndDate', beginTime)  
    });*/
});
var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		dateClass: {}
	},
	methods: {
		//==============================================
		dateDefind: function () {
			var self = this;
			$('#datetimepicker').datetimepicker({
		    	format: 'yyyy-mm',
		    	//todayBtn : "linked", 
		        autoclose: true,
		        startView: 'year',
		        minView: 'year',
		        maxView:'decade',
		        todayHighlight : true, 
		        language: 'zh-CN',
		    	pickerPosition:'bottom-left',
		    	clearBtn:true,
		    	//endDate:new Date("2017-14-01")
		    }).on('changeDate',function(e){ 		    	
		    	var beginTime = e.date;
		    	$('#datetimepicker').datetimepicker('setEndDate', beginTime); 
		    	var value = $("#datetimepicker").val();
	            self.dateClass.name = value;
		    }).on("show",function(ev) {
		    	var dt = new Date();
		    	var y = dt.getFullYear();
		    	var m = dt.getMonth()+1+2;
		    	if(m>12) {
		    		year += 1;
		    		m = 1;
		    	}
		    	var year = y+"-"+m+"-"+"01";
		    	$(this).datetimepicker('setEndDate', new Date(year));
		    });
	        //当选择器隐藏时，讲选择框只赋值给data里面的time
	        $('#datetimepicker').datetimepicker().on('hide', function (ev) {
	            var value = $("#datetimepicker").val();
	            self.dateClass.name = value;
	        });
	    },
		//==============================================
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.dateClass = {};
			this.dateDefind(); // 调用
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
            this.dateDefind(); // 调用
		},
		saveOrUpdate: function (event) {
			var url = vm.dateClass.id == null ? "../jky/dateclass/save" : "../jky/dateclass/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.dateClass),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../jky/dateclass/delete",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get("../jky/dateclass/info/"+id, function(r){
                vm.dateClass = r.dateClass;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});
