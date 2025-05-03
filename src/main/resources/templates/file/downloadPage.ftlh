<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <h2>Файл для скачивания: ${name}</h2>

    <form method="post" action="/files/download/${name}">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary">Скачать файл</button>
    </form>

    <a href="/users/profile" class="btn btn-secondary mt-2">Назад в профиль</a>
</@main.layout>