<%@page contentType="text/html;UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">

    $(function () {


        $("#btn").linkbutton({
            onClick: function () {
                var titles = $("#user_cc").combotree("getText")
                var fileds = $("#user_cc").combotree("getValues")

                var a = "";
                $.each(fileds, function (index, filed) {
                    if (index == fileds.length - 1) {
                        a += filed;
                    } else {
                        a += filed + ",";
                    }
                })
                $("#user_ff").form('submit', {
                    url: "${pageContext.request.contextPath}/daoyu",
                    queryParams: {
                        titles: titles,
                        fileds: a
                    }
                })


            }
        })

        $('#user_dg').datagrid({
            method: "post",
            url: '${pageContext.request.contextPath}/select',
            columns: [[
                {field: 'id', title: '编号', width: 100},
                {field: 'name', title: '姓名', width: 100},
                {field: 'province', title: '省份', width: 100},
                {field: 'bri', title: '生日', width: 100, align: 'right'}
            ]],
            fit: true,
            fitColumns: true,
            pagination: true,
            toolbar: [{
                text: "全部导出",
                iconCls: 'icon-edit',
                handler: function () {
                    alert('编辑按钮')
                    window.location.href = "${pageContext.request.contextPath}/allDaochu";
                }
            }, '-', {
                text: "全部导入",
                iconCls: 'icon-help',
                handler: function () {
                    alert('帮助按钮')
                    $("#daoruDiv").dialog("open");
                }
            }, '-', {
                text: "自定义导出",
                iconCls: 'icon-help',
                handler: function () {
                    $("#user_dd").dialog("open")
                }
            }]
        });

        $('#daoruDiv').dialog({
            title: 'My Dialog',
            width: 400,
            height: 200,
            closed: true,
            cache: false,
            modal: true
        });
    })

    function dianjij() {
        $("#daoruFrom").form("submit", {
            url: "${pageContext.request.contextPath}/daoru",
            onSubmit: function () {
                return $(this).form("validate");
            },
            success: function (data) {
                if (data == "true") {
                    $("#daoruDiv").dialog('open');
                    $("#user_dg").datagrid("reload");
                } else {
                    alert(data)
                }

            }
        })
    }
</script>
<table id="user_dg"></table>

<div id="user_dd" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <div style="text-align: center">
        <select id="user_cc" class="easyui-combotree" style="width:200px;" data-options="required:true,checkbox:true,multiple:true,onlyLeafCheck:true,
data:[{
		text: '请选择',
		state: 'closed',
		children: [{
		    id:'id',
			text: '编号'
		},{
		id:'name',
			text: '名字'
		},{
		id:'bri',
			text: '生日'
		},{
		id:'province',
			text: '省份'
		}]
	}]"></select>
        <form id="user_ff" method="post">

            <a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">确定</a>

        </form>
    </div>

</div>

<div id="daoruDiv">
    <form method="post" id="daoruFrom" enctype="multipart/form-data">
        <input type="file" name="file">
        <a href="javaScript:void(0)" onclick="dianjij()">提交</a>
    </form>
</div>