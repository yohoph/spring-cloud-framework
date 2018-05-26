<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ADMIN</title>
    <style type="text/css" media="screen">
        body {
            overflow: hidden;
        }

        #editor {
            width: 100%;
            height: 600px;
        }
    </style>
</head>
<body>
    <form id="formEdit" action="edit.do" method="post">
        <input name="id" type="hidden" value="${propertiesFile.id!}"/>
        application:<input name="application" type="text" value="${propertiesFile.application!}" size="16"/>
        profile:<input name="profile" type="text" value="${propertiesFile.profile!}" size="4"/>
        label:<input name="label" type="text" value="${propertiesFile.label!'master'}" size="4"/>
        <input id="content" name="content" type="hidden" value=""/>
        <input type="button" value="submit" onclick="check();" />
    </form><br/>
    <div id="editor">${propertiesFile.content!}</div>
    <script src="../js/ace.js" type="text/javascript" charset="utf-8"></script>
    <script>
        var editor = ace.edit("editor");
        editor.setTheme("ace/theme/eclipse");
        editor.session.setMode("ace/mode/properties");

        function check(){
            if(confirm("submit ?")){
                document.getElementById("content").value = editor.getValue();
                document.getElementById("formEdit").submit();
            }
        }
    </script>
</body>
</html>