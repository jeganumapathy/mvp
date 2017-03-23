In MVP, the Presenter contains the UI business logic for the View. All invocations from the View delegate directly to Presenter. The Presenter is also decoupled directly from the View and talks to it through an interface. This is to allow mocking of the View in a unit test. One common attribute of MVP is that there has to be a lot of two-way dispatching. For example, when someone clicks the "Save" button, the event handler delegates to the Presenter's "OnSave" method. Once the save is completed, the Presenter will then call back the View through its interface so that the View can display that the save has completed.


Two primary variations

Passive View: The View is as dumb as possible and contains almost zero logic. The Presenter is a middle man that talks to the View and the Model. The View and Model are completely shielded from one another. The Model may raise events, but the Presenter subscribes to them for updating the View. In Passive View there is no direct data binding, instead the View exposes setter properties which the Presenter uses to set the data. All state is managed in the Presenter and not the View.

Pro: maximum testability surface; clean separation of the View and Model
Con: more work (for example all the setter properties) as you are doing all the data binding yourself.
Supervising Controller: The Presenter handles user gestures. The View binds to the Model directly through data binding. In this case it's the Presenter's job to pass off the Model to the View so that it can bind to it. The Presenter will also contain logic for gestures like pressing a button, navigation, etc.

Pro: by leveraging databinding the amount of code is reduced.
Con: there's less testable surface (because of data binding), and there's less encapsulation in the View since it talks directly to the Model.
