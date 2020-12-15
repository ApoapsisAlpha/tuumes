# Welcome to TUUMES!

Running the Program:
    1. Close the project vie File -> Close Project
    2. Open project, then go to the phase 2 folder and open pom.xml (Make sure to double click on the pom.xml file)
    3. At the bottom right of this file, there should be a notification about non-managed pom.xml file, click on Add as
    maven project. Wait until your ide is finished installing Maven.
    4. On the right side of your ide should now be a "m" symbol named Maven, click to open the Maven menu. Click the "m"
    symbol and type in sprint-boot:run
    5. The program should now be running.

    If these instructions don't work for you, there is a more detailed guide with images here:
        https://u.mkn.cx/phase2/detailed_instructions.png

Extensions:
    1) Enhance the user's messaging experience by allowing them to "mark as unread", delete, or archive messages after
        reading them.
    2) Requesting a schedule by day, by time, or all of the events that a user has signed up for to print/download.
    3) Replace your text UI with a Graphic User Interface (GUI), which should follow the Model-View-Presenter architecture.
    4) Extending the type of event to include VIP only events. This introduces a new type of user, which is a VIP
        attendee. The program checks to see if a user is VIP before allowing them to sign up for a VIP event.

Major Extension:
    1) We implemented a website as our GUI with MVP which allows multiple people to be logged in at the same time,
        compared to our phase 1 that only allowed one user to be logged in at once.

# Few Notes to keep in mind

Strange quirks of SpringBoot
    We used SpringBoot, which helped us get our website and API running. There is a feature that SpringBoot has that helped
        us use the MVP design pattern with our app. By adding annotations like @RestController, @Component, etc at the top
        of some of our files, SpringBoot automatically handles the initialization and hooks to attach to our API for our
        website. This is a problem because IntelliJ Community Edition doesn't support SpringBoot, so it marks all the events
        as not used while IntelliJ Ultimate knows they are used by Spring.

Request classes missing constructors
    Within our controllers sub-folders, there are folders and files that end with Request. These are the classes we use
        for forms within our website. They do not have constructors and this was intentional since SpringBoot automatically
        hooks onto them. We've tried to add constructors ourselves but it errors out with some NullPointerException errors.

Questions?
    If you have any questions about our design choices, feel free to reach out to us!