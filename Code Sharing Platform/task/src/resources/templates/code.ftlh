<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Code</title>
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
    <script>
        hljs.initHighlightingOnLoad()
    </script>
   </head>
<body>
    <span id="load_date">${date}</span>
    <pre id="code_snippet"><code> ${code}</code></pre>
    <#if (time >= 0)>
    <span id="time_restriction">The code will be available for ${time} seconds</span>
    </#if>
    <#if (views >= 0)>
    <span id="views_restriction">${views} more views allowed</span>
    </#if>
</body>
</html>