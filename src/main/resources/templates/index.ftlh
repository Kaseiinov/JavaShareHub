<#import "layout.ftlh" as main>
<@main.layout ; spring>

    <#list files as file>
        <div class="card w-100 mb-3" style="width: 18rem;">
            <div class="card-body">
                <h5 class="card-title">Name - ${file.fileName}</h5>
                <p>Status - ${file.status}</p>
                <#if file.status == 'PUBLIC'>
                    <a href="/files/download/${file.fileName}" class="card-link" download>Download</a>
                </#if>
            </div>
        </div>
    </#list>

    <nav >
        <div class="d-flex justify-content-center">
            <ul class="pagination">
                <#if hasPrevious>
                    <li class="page-item ">
                        <a class="page-link" href="/?page=${currentPage - 1}" >Previous</a>
                    </li>
                <#else>
                    <li class="page-item disabled">
                        <a class="page-link" href="/?page=${currentPage - 1}" >Previous</a>
                    </li>
                </#if>
                <#list 0..(totalPages - 1) as page>
                    <li class="page-item <#if page == currentPage>active</#if>">
                        <a class="page-link" href="/?page=${page}">${page + 1}</a>
                    </li>
                </#list>
                <#if hasNext>
                    <li class="page-item">
                        <a class="page-link" href="/?page=${currentPage + 1}">Next</a>
                    </li>
                <#else>
                    <li class="page-item disabled">
                        <a class="page-link" href="/?page=${currentPage + 1}">Next</a>
                    </li>
                </#if>

            </ul>
        </div>
    </nav>

</@main.layout>