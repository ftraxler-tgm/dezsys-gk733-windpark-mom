# DEZSYS_GK733_WINDPARK_MOM

## Einführung

Diese Übung soll die Funktionsweise und Implementierung von eine Message Oriented Middleware (MOM) mit Hilfe des **Frameworks Apache Active MQ** demonstrieren. **Message Oriented Middleware (MOM)** ist neben InterProcessCommunication (IPC), Remote Objects (RMI) und Remote Procedure Call (RPC) eine weitere Möglichkeit um eine Kommunikation zwischen mehreren Rechnern umzusetzen.

Die Umsetzung basiert auf einem praxisnahen Beispiel einer Windkraftanalage. Ein Windkraftanalage (Windrad) ist immer Teil eines Verbunds, genannt Windpark. Jede Windkraftanlage beinhaltet einen Rechner, der die Daten der Windkraftanalage aufzeichnet und diese steuern kann. Die Daten werden als REST Schnittstelle in XML oder JSON zur Verfügung gestellt. Die Daten aller Windkraftanlagen eines Windparks werden von einem Parkrechner gesammelt und abgespeichert. Der Parkrechner kommuniziert mit dem Rechenzentrum in der Zentrale. Eine Zentrale kommuniziert mit mehreren Windparks und steuert diese.

![Windpark Architektur](https://elearning.tgm.ac.at/draftfile.php/2668/user/draft/584681179/dezsys_windpark.png)

## 1.1 Ziele  

Das Ziel dieser Übung ist die **Implementierung einer Kommunikationsplattform für einen Windpark. Dabei erfolgt ein Datenaustausch von mehreren Windkraftanlage mit dem Parkrechner unter Verwendung einer Message Oriented Middleware (MOM)**. Die einzelnen Daten der Windkraftanlage sollen an den Parkrechner ü<span>bertragen werden</span>. Es sollen **nachrichtenbasierten Protokolle mit Message Queues** verwendet werden. Durch diese lose Kopplung kann gewährleistet werden, dass in Zukunft weitere Anlagen hinzugefügt bzw. Kooperationspartner eingebunden werden können.

Neben der REST-Schnittstelle muss nun eine Schnittstelle bei der Windkraftanlage implementiert werden, die in regelmässigen Abständen die Daten von der REST-Schnittstelle ausliest und diese in eine Message Queue von **Apache Active MQ** hinzufügt. Um die Datenintegrität zu garantieren, sollen jene Daten, die mit der Middleware übertragen werden in einer LOG-Datei abgespeichert werden.  

## 1.2 Voraussetzungen

*   Grundlagen Architektur von verteilten Systemen
*   Grundlagen zur nachrichtenbasierten Systemen / Message Oriented Middleware  

*   Verwendung des Message Brokers Apache ActiveMQ
*   Verwendung der XML- oder JSON Datenstruktur der Windkraftanlage (siehe GK7.3.2)  

*   Verwendung der JMSChat.jar JAVA Applikation als Startpunkt für diese Aufgabenstellung  

## 1.3 Aufgabenstellung

Implementieren Sie die Windpark-Kommunikationsplattform mit Hilfe des Java Message Service. Verwenden Sie Apache ActiveMQ ([http://activemq.apache.org](http://activemq.apache.org/)) als Message Broker Ihrer Applikation. Das Programm soll folgende Funktionen beinhalten:

 *   Installation von Apache ActiveMQ am Parkrechner.
 *   Jede Windkraftanlage erstellt eine Message Queue mit einer ID am Parkrechner.
 *   Jede Windkraftanlage legt in regelmässigen Abständen die Daten der Anlage in der Message Queue ab.
 *   Bei einer erfolgreichen Übertragung empfängt die Windkraftanlage die Nachricht "SUCCESS".
 *   Der Parkrechner fragt in regelmässigen Abständen alle Message Queues ab.
 *   Der Parkrechner <span>f<span style="">ügt alle Daten aller Windkraftanlagen zusammen und stellt diese an einer REST Schnittstelle im JSON/XML Format zur Verfügung.  

## 1.4 Bewertung  

 *   Gruppengrösse: 1 Person  
 *   Anforderungen **"überwiegend erfüllt"**

	 *   Implementierung der Kommunikation zwischen einer Windkraftanlage und dem Parkrechner (JMS Queue)  

	 *   Implementierung der REST Schnittstelle am Parkrechner
		*   Zusammensetzung der Daten aller Windkraftanlagen in eine zentrale JSON/XML-Struktur

 *   Anforderungen **"zur Gänze erfüllt"**

	 *   Implementierung der Kommunikation mit mehreren Windkraftanlage und dem Parkrechner  

	 *   Rückmeldung des Ergebnisses der Übertragung vom Parkrechner an die Windkraftanlage (JMS: Topic)  

	 *   LOG-Datei bei jeder Windkraftanlage
	 *   LOG-Datei für den Parkrechner mit allen eingehenden Daten aller Windkraftanlagen

## 1.5 Fragestellung für Protokoll

 *   Nennen Sie mindestens 4 Eigenschaften der Message Oriented Middleware?  

  * Nachrichten werden in eine Queue gesendet.
  * eine Asynchrone Kommunikation
  * Die Kommunikation kann Minuten dauern
  * Die Grund Idea ist Nachricht in die Queue schicken

 *   Was versteht man unter einer transienten und synchronen Kommunikation?
* Bei der transienten Kommunikation werden die Nachrichten nur so lange gespeichert solang Sender und Reciver laufen.
* Der Sender wird blockiert solange bis es gebuffert und angekommen ist beim Reciver und er die Nachricht verarbeitete hat.  
 *   Beschreiben Sie die Funktionsweise einer JMS Queue?

 Jeder kann etwas hineinstecken und jeder kann etwas herausnehmen. Jeder ist lose gekoppelt niemand ist von irgendwem abhängig.

 *   JMS Overview - Beschreiben Sie die wichtigsten JMS Klassen und deren Zusammenhang?

 *   Beschreiben Sie die Funktionsweise eines JMS Topic?
* Es gibt Publisher und Subscriber wenn man etwas published geht dies an alle Subscriber.

 *   Was versteht man unter einem lose gekoppelten verteilten System? Nennen Sie ein Beispiel dazu. Warum spricht man hier von lose?
* Die Änderungen haben nur eine lokale Auswirkung weshalb es einfacher ist zu implementieren. Wenn ich einen Newsletter Subscriber. Weil der man nur empfängt und für sich selbst ist.

## 1.6 Links & Dokumente

 *   Grundlagen Message Oriented Middleware: [Präsentation](https://elearning.tgm.ac.at/pluginfile.php/84683/mod_resource/content/2/dezsys_mom.pdf)
 *   Middleware: [Apache ActiveMQ Installationspaket](http://activemq.apache.org/activemq-5153-release.html)
 *   Apache ActiveMQ & JMS Tutorial:
		*   http://activemq.apache.org/index.html
		*   http://www.academictutorials.com/jms/jms-introduction.asp
		*   http://docs.oracle.com/javaee/1.4/tutorial/doc/JMS.html#wp84181
		*   http://www.oracle.com/technetwork/systems/middleware/jms-basics-jsp-135286.html
		*   http://www.oracle.com/technetwork/articles/java/introjms-1577110.html
		*   https://spring.io/guides/gs/messaging-jms/
		*   https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-messaging.html
		*   https://dzone.com/articles/using-jms-in-spring-boot-1
