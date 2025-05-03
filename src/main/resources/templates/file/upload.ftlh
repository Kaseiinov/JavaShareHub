<#import "../layout.ftlh" as main>
<@main.layout ; spring>

    <style>

        h2 {
            color: red;
            text-align: center;
            margin-bottom: 30px;
        }
    </style>

    <h2>Иземенение пользователя</h2>

    <form action="/files/upload" method="post" enctype="multipart/form-data">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                   value="${(_csrf.token)!'csrf-token'}"/>
        </#if>

        <div class="mb-3">
            <label for="disabledSelect" class="form-label">Категория:</label>
            <select name="categoryId" id="disabledSelect" class="form-select">
                <option>Выберите категорию</option>
                <#if categories??>
                    <#list categories as category>
                        <option value="${category.id}">${category.name}</option>
                    </#list>
                </#if>
            </select>
<#--            <@spring.showErrors "<br>" "error text-danger" />-->
        </div>

        <div class="mb-3">
            <label for="disabledSelect" class="form-label">Статус:</label>
            <select name="status" id="disabledSelect" class="form-select">
                <option>Выберите категорию</option>
                <option value="PUBLIC">PUBLIC</option>
                <option value="PRIVATE">PRIVATE</option>
            </select>
            <#--            <@spring.showErrors "<br>" "error text-danger" />-->
        </div>

        <div class="mb-3">
            <label for="formFileMultiple" class="form-label">File</label>
            <input name="file" class="form-control" type="file" id="formFileMultiple">
<#--            <@spring.showErrors "<br>" "error text-danger" />-->
        </div>

        <button type="submit" class="btn btn-success w-100 mb-3">Сохранить</button>
    </form>

</@main.layout>
