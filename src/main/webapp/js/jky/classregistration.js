$(function () {
	queryDateDefind('#dateclassame',vm.q.dateclassame), // 在加载页面的时候给查询框加上日期控件
	$("#dateclassame").prop("placeholder",getYearMont());
	$.ajax({
		type: "GET",
	    url: "../jky/classregistration/getCrIdCount?dt="+getYearMont(),
	    success: function(r){
	    	console.log(r);
			if(r.isTrue){
				vm.showDown = true;
			}
		}
	});
    $("#jqGrid").jqGrid({
        url: '../jky/classregistration/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', width: 50, key: true,hidden:true },
			{ label: '日期', name: 'day', width: 80 }, 			
			{ label: '星期', name: 'week', width: 80 }, 			
			{ label: '起止日期', name: 'startFinishDate', width: 80 }, 			
			{ label: '班级', name: 'classRoom', width: 80 }, 			
			{ label: '授课地点', name: 'adress', width: 80 }, 			
			{ label: '课程内容', name: 'content', width: 80 }, 			
			{ label: '课时折算（节）', name: 'classCount', width: 80 }			
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
	
});

getYearMont = function() {
	var dt = new Date();
	m = dt.getMonth()+1;
	m = m<10 ? "0"+m : m;
	return dt.getFullYear()+"-"+m;
	
}
// 自定义的函数在这里可以使用vm
queryDateDefind= function (obj,inputValue) {
	$(obj).datetimepicker({
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
    	$(obj).datetimepicker('setEndDate', beginTime); 
    	var value = $(obj).val();
    	//vm.q.dateclassame = value;
    	inputValue = value;
    }).on("show",function(ev) {
    	var dt = new Date();
    	var y = dt.getFullYear();
    	var m = dt.getMonth()+1+2;
    	if(m>12) {
    		year += 1;
    		m = 1;
    	}
    	var year = y+"-"+m+"-"+"01";
    	$(obj).datetimepicker('setEndDate', new Date(year));
    }).on('hide', function (ev) {
        var value = $(obj).val();
        inputValue = value;
    });
}


var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		showDown: false,
		q:{
			dateclassame: null
		},
		classRegistration: {
			startDate:"", // 把实体里没有的显示出来
			endDate:"",
			isWeek:false,
			isNight:false,
			yearMont:""
		}
	},
	methods: {
		//==============================================
		dateDefind: function () {
			var obj = '#yd';
			var inputValue = vm.classRegistration.yearMont;
			queryDateDefind(obj,inputValue);
	    },
		//==============================================
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.classRegistration = {};
			vm.dateDefind();
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
            this.dateDefind();
		},
		down: function(ev) {
			var c = vm.q.dateclassame;
			if(!c) {
				c = getYearMont();
			}
			location.href = "../jky/classregistration/down?n=1&dt=" + c;
		},
		down2: function(ev) {
			var c = vm.q.dateclassame;
			if(!c) {
				c = getYearMont();
			}
			location.href = "../jky/classregistration/down?n=2&dt=" + c;
		},
		saveOrUpdate: function (event) {
			var url = vm.classRegistration.id == null ? "../jky/classregistration/save" : "../jky/classregistration/update";
			vm.classRegistration.yearMoth = $("#yd").val();
			console.log(vm.classRegistration)
			var datas = JSON.stringify(vm.classRegistration)
			$.ajax({
				type: "POST",
			    url: url,
			    data: datas,
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
				    url: "../jky/classregistration/delete",
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
			$.get("../jky/classregistration/info/"+id, function(r){
                vm.classRegistration = r.classRegistration;
            });
		},
		reload: function (event) {
			queryDateDefind('#dateclassame',vm.q.dateclassame),
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$.ajax({
				type: "GET",
			    url: "../jky/classregistration/getCrIdCount?dt="+vm.q.dateclassame,
			    success: function(r){
					if(r.isTrue){
						vm.showDown = true;
					}
				}
			});
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'dt': vm.q.dateclassame}, // 查询自己可以新增参数
                page:page
            }).trigger("reloadGrid");
		}
	}
});

vm.$watch('dt', function (newValue, oldValue) {
	 console.log('inner:', newValue) // 后输出 "inner" 2
})