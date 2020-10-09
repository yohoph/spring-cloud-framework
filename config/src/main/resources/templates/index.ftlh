<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ADMIN</title>
</head>
<body>
    <form id="formEdit" action="index" method="post">
        application:<input name="application" type="text" value="${propertiesFile.application!}" size="16" />
        profile:<input name="profile" type="text" value="${propertiesFile.profile!}" size="4"/>
        label:<input name="label" type="text" value="${propertiesFile.label!'master'}" size="4"/>
        <input type="submit" value="search"/> <input type="button" value="add" onclick="javascript:window.location='edit'"/>
    </form>
    <br/>
    <table border="1">
        <tr> <td>application</td> <td>profile</td> <td>label</td> <td>user</td> <td>date</td> <td>operation</td> </tr>
        <#list propertiesFileList as item>
        <tr>
            <td>${item.application}</td> <td>${item.profile}</td> <td>${item.label}</td>
            <td>${item.lastUpdateUserId}</td> <td>${item.lastUpdateDate}</td>
            <td>
                <a href="edit?id=${item.id}">edit</a>
                <a href="delete.do?id=${item.id}" onclick="javascript:return confirm('delete ?');">delete</a>
            </td>
        </tr>
        </#list>
    </table>
</body>
</html>