<h1>ArrumaUFPR</h1>


<p align="center">
<a href="#about-memo">Sobre</a>&nbsp;&nbsp;&nbsp; | &nbsp;&nbsp;&nbsp;
<a href="#tecnologies-rocket">Tecnologias</a>&nbsp;&nbsp;&nbsp; | &nbsp;&nbsp;&nbsp;
<a href="#install-scroll">Como instalar</a>&nbsp;&nbsp;&nbsp; | &nbsp;&nbsp;&nbsp;
</p>


## Sobre :memo:

O objetivo deste sistema é auxiliar profissionais de manutenção a organizar, controlar e priorizar as atividades de gestão, com o auxílio dos usuários para indicar as não conformidades dos edifícios.

Os objetivos específicos são:

- Permitir que o usuário crie solicitações personalizadas, a fim de auxiliar o time de manutenção nos descobrimentos de novas tarefas de manutenção;
- Permitir que o profissional de manutenção acompanhe as atividades criadas, podendo definir o grau de urgência das tarefas;
- Permitir que o usuário realize cadastros de novos equipamentos, locais e tipo de incidentes no software;
- Permitir que o usuário possa exportar um relatório dos incidentes abertos por ele;
- Permitir que o profissional de manutenção possa exportar relatórios de cadastros e incidentes;

## Tecnologias :rocket:

- Java
- SprintBoot
- Thymeleaft
- Postgres


## Como instalar :scroll:

1. Clonar projeto
2. Instalar dependencias via MAVEN
3. Rodar SQLs do arquivo SQLS/database.sql para criar o banco de dados e inserir alguns registros
4. Rodar projeto e abrir no localhost:8080

---


./mvnw clean package -DskipTests


docker-compose up --remove-orphans --force-recreate --build

docker-compose down -v


