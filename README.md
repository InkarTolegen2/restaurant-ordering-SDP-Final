# Restaurant Ordering System

This is our group project for the Software Design Patterns course. It is a small restaurant ordering app: a guest picks dishes, the kitchen cooks them, and the guest can see on their phone or laptop when the order is ready.

## What the app does

Imagine a guest sitting at table 7. They open the menu, choose a pizza, add extra cheese and a large size, and the price updates right away. They put it in the cart, type the table number, choose a discount (or enter a promo code), pick card or cash and send the order. In the kitchen, the cook sees the new order and presses "Start cooking", and later "Mark ready". The guest's page updates by itself every couple of seconds: first "Your order is received", then "Being cooked", then "Ready".

We built it to show that design patterns are not just theory. In this project six of them work together, and each one solves a real problem in the flow above.

## Who did what

Magzhan worked on the Factory and Decorator patterns. He made the meal classes and MealFactory, which creates a pizza, a burger or a drink by its type. He also made the toppings (extra cheese, sauce, large size) as decorators: each topping wraps a meal and adds its own price and description, so we do not need a separate class for every combination. Magzhan also built the menu page with the price recalculation and wrote the report part about these two patterns.

Alexander worked on the Builder and Observer patterns. He made OrderBuilder, which collects the dishes, the table number and the comment and checks them in build(), so an order without dishes or without a table cannot be created. He also made the Order class with the statuses NEW, COOKING and READY. When the status changes, KitchenListener and CustomerListener are notified automatically, and Order does not even need to know who is listening. Alexander also built the cart, the order status page and the kitchen panel, and wrote the report part about Builder and Observer with the sequence diagram.

Inkar Tolegen worked on the Strategy and Facade patterns. She made the discount strategies (no discount, a percent discount and a promo code), so the way the price is calculated can be swapped without touching the rest of the code. She also made RestaurantFacade: one placeOrder() call runs the whole chain of factory, decorators, builder, discount and listeners. On top of it she built the REST API and the checkout part of the cart page. Inkar also wrote the report part about Strategy and Facade, the general class diagram, the introduction and the conclusion.

Together we connected the pages with the server, tested the whole path of an order, fixed bugs, made the screenshots and prepared the demo.

## What we used

The backend is written in Java 17 with Maven, and the tests use JUnit 5. The REST API runs on the HTTP server built into the JDK, so no extra libraries are needed. The web pages are plain HTML, CSS and JavaScript, and they learn about status changes by asking the server every few seconds. If the server is not running, the pages still work in a demo mode and keep the orders in the browser, which is handy for showing the interface.

## How to run

You need a JDK 17 or newer. On Windows double-click run.bat, on macOS or Linux run ./run.sh, or just start the App class in IntelliJ IDEA. Then open http://localhost:7070 in your browser and keep the console window open. To see the Observer work, open kitchen.html in a second tab, press "Start cooking" there and watch the status page of your order change by itself. The messages from the kitchen and the customer listeners also appear in the server console. The tests run with "mvn test" in the backend folder.

## Conclusion

The project follows the whole life of an order, from choosing a dish to the moment the kitchen marks it ready. Every pattern has its own job: Factory and Decorator make the menu flexible, Builder keeps orders valid, Strategy makes discounts replaceable, Observer tells the kitchen and the guest about every change, and Facade lets the web pages use all of this through a few simple requests. Because our parts met only on small interfaces, each of us could write and test our own part first and then connect everything.

The project has limits. Orders are stored in memory and disappear when the server stops, there is no login, and payment is only a choice between card and cash. In the future we would add a database, use WebSocket instead of polling and add more kinds of discounts.
