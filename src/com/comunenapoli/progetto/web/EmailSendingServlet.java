package com.comunenapoli.progetto.web;

import java.io.IOException;
 
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comunenapoli.progetto.utils.Costanti;
import com.comunenapoli.progetto.utils.EmailUtility;
 

@WebServlet("/emailSendingServlet")
public class EmailSendingServlet extends HttpServlet {
  
	private static final long serialVersionUID = 1L;
	
	private String host;
    private String port;
    private String user;
    private String pass;
 
    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }
 
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // reads form fields
    	String recipient = request.getParameter("recipient");
    	Boolean isPromosso = (Boolean) request.getAttribute(Costanti.UTENTE_PROMOSSO);
    	Boolean isRimosso = (Boolean) request.getAttribute(Costanti.UTENTE_RIMOSSO);
    	Boolean isVerificato = (Boolean) request.getAttribute(Costanti.UTENTE_VERIFICATO);
    	Boolean isCancellato = (Boolean) request.getAttribute(Costanti.CANCELLAZIONE_AVVENUTA);
    	
        String subject = "";
        String content = "";
 
        String resultMessage = "";
        if (isPromosso!=null && isPromosso) {
        	subject = "NoleJava - Account promosso";
        	content ="<html>\n" + 
        			"<style type=\"text/css\">\n" + 
        			"body{background-color: #88BDBF;margin: 0px;}\n" + 
        			"</style>\n" + 
        			"<body>\n" + 
        			"	<table border=\"0\" width=\"50%\" style=\"margin:auto;padding:30px;background-color: #F3F3F3;border:1px solid #f7b71d;\">\n" + 
        			"		<tr>\n" + 
        			"			<td>\n" + 
        			"				<table border=\"0\" width=\"100%\">\n" + 
        			"					<tr>\n" + 
        			"						<td>\n" + 
    				"							<h1><a href=\"http://localhost:8080/Nolejava/\"><img src=\"https://i.ibb.co/HtBghxc/logo-pdf.png\" alt=\"logo-pdf\" border=\"0\"></a></h1></td>\n" + 
        			"						<td>\n" + 
        			"						</td>\n" + 
        			"					</tr>\n" + 
        			"				</table>\n" + 
        			"			</td>\n" + 
        			"		</tr>\n" + 
        			"		<tr>\n" + 
        			"			<td>\n" + 
        			"				<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"text-align:center;width:100%;background-color: #fff;\">\n" + 
        			"					<tr>\n" + 
        			"						<td style=\"background-color:#f7b71d;height:100px;font-size:20px;color:#fff;\"><h3 style=\"padding-top:25px;\">" + subject + "</h3></td>\n" + 
        			"					</tr>\n" + 
        			"					<tr>\n" + 
        			"						<td>\n" + 
        			"						</td>\n" + 
        			"					</tr>\n" + 
        			"					<tr>\n" + 
        			"						<td>\n" + 
        			"							<p style=\"padding:0px 100px;\">";
        	content += "Sei stato promosso a membro dello staff. Congratulazioni";
            content += ", ora puoi effetuare il login. www.nolejava.com/login";
            content += "</p>\n" + 
        			"						</td>\n" + 
        			"					</tr>\n" + 
        			"					<tr>\n" + 
        			"						<td>\n" + 
        			"							<button onclick=\"location.href='http://localhost:8080/Nolejava/jsp/login.jsp'\" style=\"margin:10px 0px 30px 0px;border-radius:4px;padding:10px 20px;border: 0;color:#fff;background-color:#f7b71d; \">Accedi adesso</button>\n" + 
        			"						</td>\n" + 
        			"					</tr>\n" + 
        			"				</table>\n" + 
        			"			</td>\n" + 
        			"		</tr>\n" + 
        			"		<tr>\n" + 
        			"			<td>\n" + 
        			"				<table border=\"0\" width=\"100%\" style=\"border-radius: 5px;text-align: center;\">\n" + 
        			"					<tr>\n" + 
        			"						<td>\n" + 
        			"							<h3 style=\"margin-top:10px;\"></h3>\n" + 
        			"						</td>\n" + 
        			"					</tr>\n" + 
        			"\n" + 
        			"					<tr>\n" + 
        			"						<td>\n" + 
        			"							<div style=\"margin-top: 20px;\">\n" + 
        			"								<span style=\"font-size:12px;\">Breakout Room 4</span><br>\n" + 
        			"								<span style=\"font-size:12px;\">Copyright © 2020 Nolejava</span>\n" + 
        			"							</div>\n" + 
        			"						</td>\n" + 
        			"					</tr>\n" + 
        			"				</table>\n" + 
        			"			</td>\n" + 
        			"		</tr>\n" + 
        			"	</table>\n" + 
        			"</body>\n" + 
        			"</html>";	

        }
        else if (isRimosso!=null && isRimosso) {
        	subject = "NoleJava - Account cancellato";
        	content = "Il tuo account è stato cancellato dai nostri sistemi.";
            content += "</p>\n" + 
         			"						</td>\n" + 
         			"					</tr>\n" + 
         			"					<tr>\n" + 
         			"						<td>\n" + 
         			"							<button onclick=\"location.href='http://localhost:8080/Nolejava/jsp/login.jsp'\" style=\"margin:10px 0px 30px 0px;border-radius:4px;padding:10px 20px;border: 0;color:#fff;background-color:#f7b71d; \">Accedi adesso</button>\n" + 
         			"						</td>\n" + 
         			"					</tr>\n" + 
         			"				</table>\n" + 
         			"			</td>\n" + 
         			"		</tr>\n" + 
         			"		<tr>\n" + 
         			"			<td>\n" + 
         			"				<table border=\"0\" width=\"100%\" style=\"border-radius: 5px;text-align: center;\">\n" + 
         			"					<tr>\n" + 
         			"						<td>\n" + 
         			"							<h3 style=\"margin-top:10px;\"></h3>\n" + 
         			"						</td>\n" + 
         			"					</tr>\n" + 
         			"\n" + 
         			"					<tr>\n" + 
         			"						<td>\n" + 
         			"							<div style=\"margin-top: 20px;\">\n" + 
         			"								<span style=\"font-size:12px;\">Breakout Room 4</span><br>\n" + 
         			"								<span style=\"font-size:12px;\">Copyright © 2020 Nolejava</span>\n" + 
         			"							</div>\n" + 
         			"						</td>\n" + 
         			"					</tr>\n" + 
         			"				</table>\n" + 
         			"			</td>\n" + 
         			"		</tr>\n" + 
         			"	</table>\n" + 
         			"</body>\n" + 
         			"</html>";	
        }
        else if (isVerificato!=null && isVerificato) {
        	subject = "NoleJava - Account verificato con successo";
        	content ="<html>\n" + 
        			"<style type=\"text/css\">\n" + 
        			"body{background-color: #88BDBF;margin: 0px;}\n" + 
        			"</style>\n" + 
        			"<body>\n" + 
        			"	<table border=\"0\" width=\"50%\" style=\"margin:auto;padding:30px;background-color: #F3F3F3;border:1px solid #f7b71d;\">\n" + 
        			"		<tr>\n" + 
        			"			<td>\n" + 
        			"				<table border=\"0\" width=\"100%\">\n" + 
        			"					<tr>\n" + 
        			"						<td>\n" + 
    				"							<h1><a href=\"http://localhost:8080/Nolejava/\"><img src=\"https://i.ibb.co/HtBghxc/logo-pdf.png\" alt=\"logo-pdf\" border=\"0\"></a></h1></td>\n" + 
        			"						<td>\n" + 
        			"						</td>\n" + 
        			"					</tr>\n" + 
        			"				</table>\n" + 
        			"			</td>\n" + 
        			"		</tr>\n" + 
        			"		<tr>\n" + 
        			"			<td>\n" + 
        			"				<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"text-align:center;width:100%;background-color: #fff;\">\n" + 
        			"					<tr>\n" + 
        			"						<td style=\"background-color:#f7b71d;height:100px;font-size:20px;color:#fff;\"><h3 style=\"padding-top:25px;\">" + subject + "</h3></td>\n" + 
        			"					</tr>\n" + 
        			"					<tr>\n" + 
        			"						<td>\n" + 
        			"						</td>\n" + 
        			"					</tr>\n" + 
        			"					<tr>\n" + 
        			"						<td>\n" + 
        			"							<p style=\"padding:0px 100px;\">";
            content += "Il tuo account è stato verificato";
            content += ", ora puoi effetuare il login. www.nolejava.com/login";
            content += "</p>\n" + 
        			"						</td>\n" + 
        			"					</tr>\n" + 
        			"					<tr>\n" + 
        			"						<td>\n" + 
        			"							<button onclick=\"location.href='http://localhost:8080/Nolejava/jsp/login.jsp'\" style=\"margin:10px 0px 30px 0px;border-radius:4px;padding:10px 20px;border: 0;color:#fff;background-color:#f7b71d; \">Accedi adesso</button>\n" + 
        			"						</td>\n" + 
        			"					</tr>\n" + 
        			"				</table>\n" + 
        			"			</td>\n" + 
        			"		</tr>\n" + 
        			"		<tr>\n" + 
        			"			<td>\n" + 
        			"				<table border=\"0\" width=\"100%\" style=\"border-radius: 5px;text-align: center;\">\n" + 
        			"					<tr>\n" + 
        			"						<td>\n" + 
        			"							<h3 style=\"margin-top:10px;\"></h3>\n" + 
        			"						</td>\n" + 
        			"					</tr>\n" + 
        			"\n" + 
        			"					<tr>\n" + 
        			"						<td>\n" + 
        			"							<div style=\"margin-top: 20px;\">\n" + 
        			"								<span style=\"font-size:12px;\">Breakout Room 4</span><br>\n" + 
        			"								<span style=\"font-size:12px;\">Copyright © 2020 Nolejava</span>\n" + 
        			"							</div>\n" + 
        			"						</td>\n" + 
        			"					</tr>\n" + 
        			"				</table>\n" + 
        			"			</td>\n" + 
        			"		</tr>\n" + 
        			"	</table>\n" + 
        			"</body>\n" + 
        			"</html>";	

        }
        else if (isCancellato!=null && isCancellato) {
        	subject = "NoleJava - Noleggio eliminato con successo";
        	content ="<html>\n" + 
        			"<style type=\"text/css\">\n" + 
        			"body{background-color: #88BDBF;margin: 0px;}\n" + 
        			"</style>\n" + 
        			"<body>\n" + 
        			"	<table border=\"0\" width=\"50%\" style=\"margin:auto;padding:30px;background-color: #F3F3F3;border:1px solid #f7b71d;\">\n" + 
        			"		<tr>\n" + 
        			"			<td>\n" + 
        			"				<table border=\"0\" width=\"100%\">\n" + 
        			"					<tr>\n" + 
        			"						<td>\n" + 
    				"							<h1><a href=\"http://localhost:8080/Nolejava/\"><img src=\"https://i.ibb.co/HtBghxc/logo-pdf.png\" alt=\"logo-pdf\" border=\"0\"></a></h1></td>\n" + 
        			"						<td>\n" + 
        			"						</td>\n" + 
        			"					</tr>\n" + 
        			"				</table>\n" + 
        			"			</td>\n" + 
        			"		</tr>\n" + 
        			"		<tr>\n" + 
        			"			<td>\n" + 
        			"				<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"text-align:center;width:100%;background-color: #fff;\">\n" + 
        			"					<tr>\n" + 
        			"						<td style=\"background-color:#f7b71d;height:100px;font-size:20px;color:#fff;\"><h3 style=\"padding-top:25px;\">" + subject + "</h3></td>\n" + 
        			"					</tr>\n" + 
        			"					<tr>\n" + 
        			"						<td>\n" + 
        			"						</td>\n" + 
        			"					</tr>\n" + 
        			"					<tr>\n" + 
        			"						<td>\n" + 
        			"							<p style=\"padding:0px 100px;\">";
            content += "Il tuo noleggio è stato cancellato";
            content += "</p>\n" + 
        			"						</td>\n" + 
        			"					</tr>\n" + 
        			"					<tr>\n" + 
        			"						<td>\n" + 
        			"							<button onclick=\"location.href='http://localhost:8080/Nolejava/jsp/login.jsp'\" style=\"margin:10px 0px 30px 0px;border-radius:4px;padding:10px 20px;border: 0;color:#fff;background-color:#f7b71d; \">Accedi adesso</button>\n" + 
        			"						</td>\n" + 
        			"					</tr>\n" + 
        			"				</table>\n" + 
        			"			</td>\n" + 
        			"		</tr>\n" + 
        			"		<tr>\n" + 
        			"			<td>\n" + 
        			"				<table border=\"0\" width=\"100%\" style=\"border-radius: 5px;text-align: center;\">\n" + 
        			"					<tr>\n" + 
        			"						<td>\n" + 
        			"							<h3 style=\"margin-top:10px;\"></h3>\n" + 
        			"						</td>\n" + 
        			"					</tr>\n" + 
        			"\n" + 
        			"					<tr>\n" + 
        			"						<td>\n" + 
        			"							<div style=\"margin-top: 20px;\">\n" + 
        			"								<span style=\"font-size:12px;\">Breakout Room 4</span><br>\n" + 
        			"								<span style=\"font-size:12px;\">Copyright © 2020 Nolejava</span>\n" + 
        			"							</div>\n" + 
        			"						</td>\n" + 
        			"					</tr>\n" + 
        			"				</table>\n" + 
        			"			</td>\n" + 
        			"		</tr>\n" + 
        			"	</table>\n" + 
        			"</body>\n" + 
        			"</html>";	
        }
        
 
        try {
            EmailUtility.sendEmail(host, port, user, pass, recipient, subject,
                    content);
            resultMessage = "Email inviata con successo";
        } catch (Exception ex) {
            ex.printStackTrace();
            resultMessage = "There were an error: " + ex.getMessage();
        } finally {
            request.setAttribute("Message", resultMessage);
            getServletContext().getRequestDispatcher("/jsp/private/result.jsp").forward(
                    request, response);
        }
    }
}