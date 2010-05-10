<%--<%@page import="Sistema.Email" %>--%>
<jsp:useBean id="email" class="Email"/>

<%

            String mailServer = "smtp.gmail.com";
            String assunto = request.getParameter("assunto");
            String para = "seu-Email@gmail.com";
            String de = request.getParameter("de");
            String mensagem;
            mensagem = "Assunto..:" + request.getParameter("assunto") + ""+ "E-mail..:"
                    + request.getParameter("de")+ "" + "Fone..:" + request.getParameter("fone") +
                    "" + request.getParameter("obs")+ "\n";
            email.sendMail(mailServer, assunto, para, de, mensagem);
            response.sendRedirect("ok.html");
%>   