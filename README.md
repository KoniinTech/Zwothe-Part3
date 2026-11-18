# Zwothe QuickChat Part 3

This Java with Ant project continues the supplied Zwothe registration, sign-in and messaging application for PROG5121.

## Build and run

Use JDK 17 or newer and Apache Ant. Run these commands from the project directory.

```sh
ant clean test jar
java -jar dist/ZwothePart3.jar
```

Open the folder as an existing Java with Ant project in NetBeans. The first build downloads Gson, JUnit, and Hamcrest. Keep `dist/lib` beside `ZwothePart3.jar` when moving the runnable application.

## QuickChat

Register and sign in, then enter the maximum number of messages for this session. **1. Send Messages** lets you send, disregard, or store messages. The existing **Enter the next message?** prompt lets you pause and return to the menu. **2. Show recently sent messages** prints the sent report. Sending is simulated in the console.

**4. Stored Messages** shows senders and recipients, finds the longest stored message, searches by ID or recipient, deletes stored messages by hash, and prints a full stored report. A combined recipient search covers sent and stored messages.

QuickChat loads `messages.json` at startup and saves stored-message changes there. The original Part 2 archive format is supported. Older entries display **Unknown** when they have no sender. Sent and disregarded messages belong to the current session. Entering a zero message limit still lets you view stored messages.

## Tests and walkthrough

Run `ant test` for the original signup, sign-in and messaging tests, the five Part 3 fixtures, and the archive compatibility and recovery tests. GitHub Actions runs the same commands on Windows and Ubuntu.

1. Register a demo user, sign in, and select a message limit. Send one message, choose **no** at the next-message prompt, and show the sent report.
2. Resume entering messages. Store two messages for one recipient and disregard another.
3. Open **Stored Messages**, show sender and recipient details, find the longest message, and search by ID and recipient.
4. Delete one stored message by its hash, restart with a zero limit, and show the remaining stored report.
5. Explain how the five arrays are populated, how the JSON archive is read and updated, and what the tests verify.

## Source and assistance

[1] Google, “Gson User Guide,” Array Examples. [Online]. Available: https://google.github.io/gson/UserGuide.html#array-examples

OpenAI Codex assisted with implementing and testing Part 3.
