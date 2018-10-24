<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<script type="text/javascript">
    $(function () {
        $('#dg').edatagrid({
            updateUrl: "${pageContext.request.contextPath}/banner/getSove",
            url:'${pageContext.request.contextPath}/banner/getSelect',
            fit:true,
            method:"post",
            fitColumns:true,
            pagination:true,
            toolbar:"#banner_ding",
            pageList: [5, 10, 15],
            columns:[[
                {field:'name',title:'标题',width:100},
                {field:'status',title:'状态显示y/隐藏n',width:100,editor: {
                        type: "text",
                        options: {
                            required: true
                        }
                    }
                },
                {field:'url',title:'路径',width:100,align:'right'},
                {field:'createDate',title:'创建时间',width:100},
            ]],
            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                console.log(rowData);
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/' + rowData.url + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>name: ' + rowData.name + '</p>' +
                    '<p>description: ' + rowData.description + '</p>' +
                    '<p>pash: ' + rowData.url + '</p>' +
                    '</td>' +
                    '</tr></table>';
            },
        });

        /*轮播图添加框*/
        $('#dd').dialog({
            title: 'My Dialog',
            width: 300,
            height: 200,
            closed: true,
            cache: false,
            modal: true,
            buttons:"#bannerAddDiv",

        });

        /*轮播图添加按钮*/
        $("#banner_Add").linkbutton({});
    })
    //显示轮播图添加框
    function banner_Insert() {
        $("#dd").dialog('open');
    }
    //轮播图添加
    function banner_AddDiv() {
        $("#banner_AddFrom").form('submit', {
                url:"/user/banner/getAdd",
                onSubmit: function(){
                    return $(this).form("validate");
            },
            success:function(data){
                alert(data)
                if(data == "true"){
                    $("#dd").dialog('close');
                }
                $("#dg").datagrid("reload");
            }
        });

    }

    //轮播图修改
    function banner_edit() {
        //返回第一次选中行的数据
        var row = $("#dg").edatagrid("getSelected");
        if(row == null){
            alert("请选中你要更改的数据")
        }else{
            //根据选中号返回行下标
            var index = $("#dg").edatagrid("getRowIndex", row);
            $("#dg").edatagrid("editRow", index);
        }
    }
    //轮播图删除
    function banner_Remove() {
        var row = $("#dg").edatagrid("getSelected");
        $.ajax({
            url:"${pageContext.request.contextPath}/banner/getDelete",
            type:"post",
            dataType:"json",
            data:{id:row.id},
            success:function (data) {
                $("#dg").datagrid("reload");
            }
        });
    }

    //轮播图保存
    function banner_Update() {
        $("#dg").edatagrid("saveRow");
        $("#dg").datagrid("reload");
    }
</script>

<table id="dg"></table>

<%--顶部工具栏--%>
<div id="banner_ding">
    <a class="easyui-linkbutton" onclick="banner_Insert()" data-options="iconCls:'icon-add'">添加</a>
    <a class="easyui-linkbutton" onclick="banner_edit()" data-options="iconCls:'icon-edit'">编辑</a>
    <a class="easyui-linkbutton" onclick="banner_Remove()" data-options="iconCls:'icon-remove'">删除</a>
    <a class="easyui-linkbutton" onclick="banner_Update()" data-options="iconCls:'icon-save'">保存</a>
</div>

<%--轮播图添加---开始--%>
<div id="dd" style="text-align: center">
    <form id="banner_AddFrom" method="post" enctype="multipart/form-data">
        标题名称:<input type="text" name="name"><br>
        <br>
        <input type="file" name="multipartFile"><br>
        <br>
        描述:<input type="text" name="description">
    </form>
</div>

<div id="bannerAddDiv">
    <a id="banner_Add" onclick="banner_AddDiv()" >提交</a>
</div>
<%--轮播图添加---结束--%>
