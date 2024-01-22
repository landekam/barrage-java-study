# Entertainment event project

## Task 1

Let's begin our Java story :)

Let's imagine that you are a computer science student. You have several friends who are
part of rock bands and love playing their music. However, they lack opportunities to monetize
their creativity. They need a system to keep track of the participants at their concerts, plan
their performances, announce future events, and so on.

### What to do?

* Install [IntelliJ IDEA](https://www.jetbrains.com/idea/download)
  and [Java 17 SDK](https://www.jetbrains.com/help/idea/sdk.html)
* Fork from this project into your private one and open access to your
  mentor ([AlexeyGlebovSetronica](https://github.com/AlexeyGlebovSetronica))
    * How to fork - https://docs.github.com/en/get-started/quickstart/fork-a-repo
    * How to open access - https://docs.github.com/en/account-and-profile/setting-up-and-managing-your-personal-account-on-github/managing-access-to-your-personal-repositories/inviting-collaborators-to-a-personal-repository
* Open it in Idea and run
    * `Ctrl-Shift-A` - `Execute Gradle Task` - `gradle bootRun`
    * Check if you can open http://localhost:8080/event/api/v1/events

    * Try `List All Events`
    * Try `Search All Events`, it should fail with 500
* For this task you will need to create a new branch `TASK-1` and to do following things:
    * Read carefully everything you have in `src/main/**` and `*.gradle` and try to understand.
      Everywhere you have any doubts or questions
      on what is going on - leave a text comment right in the code, e.g:
      ```java
      public static void main(String[] args) {
          SpringApplication.run(EventingApplication.class, args);
      }
      ```
    * Implement following search
      API - [EventController::searchEvents](com/setronica/eventing/web/EventController.java)
      ```
      http://localhost:8080/event/api/v1/events/search?q=text
      ```
    * Create MR and send it to your mentor. He will review it and go through your comments.

#### Misc

Don't forget to create custom docker network to avoid any inconvenient circumstances with 
other docker services and keep current environment as isolated from the other projects

I can suggest to create 
```
docker network create -d bridge --subnet 172.21.0.0/24 --gateway 172.21.0.1 dockernet
```
