//package com.employee.management.util;
//
//import javax.sql.DataSource;
//
//public class EmailProvider {
//    public void sendEmailWithImage(String toAddress, String content, String subject, byte[] imageBytes) throws MessagingException, UnsupportedEncodingException {
//        String fromAddress = "todolistorganization@gmail.com";
//        String senderName = "x organization";
//
//        DataSource source = new ByteArrayDataSource(imageBytes, "image/png");
//
//        MimeBodyPart imagePart = new MimeBodyPart();
//        imagePart.setDataHandler(new DataHandler(source));
//        imagePart.setHeader("Content-ID", "<qr_code_image>");
//        imagePart.setDisposition(MimeBodyPart.INLINE);
//
//        String htmlContent = "<html><body>";
//        htmlContent += "<p>Here's your QR code:</p>";
//        htmlContent += "<img src=\"cid:qr_code_image\" alt=\"QR Code\">";
//        htmlContent += "</body></html>";
//
//        MimeBodyPart htmlPart = new MimeBodyPart();
//        htmlPart.setContent(htmlContent, "text/html");
//
//        MimeMultipart multipart = new MimeMultipart();
//        multipart.addBodyPart(imagePart);
//        multipart.addBodyPart(htmlPart);
//
//        MimeMessage message = mailSender.createMimeMessage();
//        message.setFrom(new InternetAddress(fromAddress));
//        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
//        message.setSubject(subject);
//        message.setContent(multipart);
//
//        mailSender.send(message);
//    }
//
//}
