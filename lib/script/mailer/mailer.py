import sys
import ssl,smtplib, email
from email import encoders
from email.mime.base import MIMEBase
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText

def mailsender():
    sender_email = str(sys.argv[1])
    password = str(sys.argv[2])
    receiver_email = str(sys.argv[3])
    subject = "Estimate From Team PC Build"
    body = "Mr./Ms " + str(sys.argv[4]) +", Thank You For Using PC Build. Please Find Your Estimate For the Build " + str(sys.argv[5]) + " Attached As A PDF File. \n\n\n\nRegards,\nTeam PC Build"
    
    message = MIMEMultipart()
    message["From"] = sender_email
    message["To"] = receiver_email
    message["Subject"] = subject
    message["Bcc"] = receiver_email  

    message.attach(MIMEText(body, "plain"))

    filename = str(sys.argv[6])

    with open(filename, "rb") as attachment:
        part = MIMEBase("application", "octet-stream")
        part.set_payload(attachment.read())

    encoders.encode_base64(part)

    part.add_header(
        "Content-Disposition",
        f"attachment; filename= {filename}",
    )

    message.attach(part)
    text = message.as_string()

    context = ssl.create_default_context()
    with smtplib.SMTP_SSL("smtp.gmail.com", 465, context=context) as server:
        server.login(sender_email, password)
        server.sendmail(sender_email, receiver_email, text)

if __name__ == "__main__":
    mailsender()
