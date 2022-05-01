<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Latest</title>

    <style type="text/css">
    #code_snippet{
        border: thick double #32a1ce;
        background-color:#DCECE7;
    }

    #load_date {
        color:green;
        font-size:18px;
    }
    </style>

    <link rel="stylesheet"
           href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>

</head>
<body>
<#list code_snippets as code_snippet>
 <span id="load_date">${code_snippet.date}</span>
 <pre id="code_snippet"><code> ${code_snippet.code}</code></pre>
</#list>
</body>
</html>