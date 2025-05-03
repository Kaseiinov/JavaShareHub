<#import "../layout.ftlh" as main>
<@main.layout>
    <body class="bg-light">

    <div class="d-flex align-items-center justify-content-between mb-4">
        <div class="d-flex align-items-center">

            <i class="fas fa-user-circle me-3 rounded" style="font-size: 80px; color: #6c757d;"></i>

            <div>
                <h5>${user.email}</h5>
            </div>
        </div>

    </div>
    <#list files as file>
        <div class="card w-100 mb-3" style="width: 18rem;">
            <div class="card-body">
                <h5 class="card-title">Name - ${file.fileName}</h5>
                <p>Status - ${file.status}</p>
            </div>
        </div>
    </#list>
    </body>

</@main.layout>
