# TP3 : Event Driven Architecture

## Objectifs

- Créer une application qui permet de gérer des comptes respectant les patterns CQRS et Event Sourcing avec les Framework AXON et Spring Boot :
- Part 1 : https://www.youtube.com/watch?v=fqfg3sNIDDk

- Part 2 : https://www.youtube.com/watch?v=0MG8akH6cfU

- POC : https://www.youtube.com/watch?v=npP2GLYLW8c

## Partie 1 :

### 1. Créer un projet Spring Boot avec les dépendances suivantes :
```bash
 <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.axonframework</groupId>
            <artifactId>axon-spring-boot-starter</artifactId>
            <version>4.4.3</version>
            <exclusions>
                <exclusion>
                    <groupId>org.axonframework</groupId>
                    <artifactId>axon-server-connector</artifactId>
                </exclusion>
            </exclusions>

        </dependency>
        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <scope>runtime</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
```

### 2. Créer `baseCommand` avec les attributs suivants :

```java
package ma.ouadouch.Event_Driven_Architecture.common_api.commands;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public abstract class BaseCommand<T> {
    @TargetAggregateIdentifier
    @Getter private T id;

    public BaseCommand(T id) {
        this.id = id;
    }
}

```

### 2. Créer `CreateAccountCommand` et `CreditAccountCommand` et `DebitAccountCommand` :

```java
package ma.ouadouch.Event_Driven_Architecture.common_api.commands;

import lombok.Getter;

public class CreateAccountCommand extends BaseCommand<String>{
    @Getter
    private double initialBalance;
    @Getter
    private  String currency;
    public CreateAccountCommand(String id, double initialBalance, String currency) {
        super(id);
        this.initialBalance = initialBalance;
        this.currency = currency;
    }
}

```

```java
package ma.ouadouch.Event_Driven_Architecture.common_api.commands;

public class CreditAccountCommand extends BaseCommand<String>{
    private double amount;
    private  String currency;
    public CreditAccountCommand(String id, double amount, String currency) {
        super(id);
        this.amount = amount;
        this.currency = currency;
    }
}

```

```java
package ma.ouadouch.Event_Driven_Architecture.common_api.commands;

public class DebitAccountCommand extends BaseCommand<String>{
    private double amount;
    private  String currency;
    public DebitAccountCommand(String id, double amount, String currency) {
        super(id);
        this.amount = amount;
        this.currency = currency;
    }
}

```
### 3. Créer aggregate

```java
package ma.ouadouch.Event_Driven_Architecture.commands.aggregate;

import ma.ouadouch.Event_Driven_Architecture.common_api.commands.CreateAccountCommand;
import ma.ouadouch.Event_Driven_Architecture.common_api.enums.AccountStatus;
import ma.ouadouch.Event_Driven_Architecture.common_api.events.AccountActivatedEvent;
import ma.ouadouch.Event_Driven_Architecture.common_api.events.AccountCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class AccountAggregate {

    @AggregateIdentifier
    private String id;
    private double balance;
    private String currency;
    private AccountStatus status;

    public AccountAggregate() {
    }
    @CommandHandler
    public AccountAggregate(CreateAccountCommand command) {
        if (command.getInitialBalance() < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        AggregateLifecycle
                .apply(new AccountCreatedEvent(command.getId(), command.getInitialBalance(), command.getCurrency()));
    }
    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        this.id = event.getId();
        this.balance = event.getInitialBalance();
        this.currency = event.getCurrency();
        this.status = AccountStatus.CREATED;
        AggregateLifecycle.apply(new AccountActivatedEvent(this.id, AccountStatus.ACTIVATED));
    }
    @EventSourcingHandler
    public void on(AccountActivatedEvent event) {
        this.status = event.getAccountStatus();
    }
}
```
### 4. Créer `BaseEvent` et `AccountActivatedEvent` et `AccountCreatedEvent`
```java
package ma.ouadouch.Event_Driven_Architecture.common_api.events;

import lombok.Getter;

public abstract class BaseEvent<T> {
    @Getter
    private T id;

    public BaseEvent(T id) {
        this.id = id;
    }
}

```

```java
package ma.ouadouch.Event_Driven_Architecture.common_api.events;

import lombok.Getter;
import ma.ouadouch.Event_Driven_Architecture.common_api.enums.AccountStatus;

public class AccountActivatedEvent extends BaseEvent<String> {
    @Getter
    private AccountStatus accountStatus;

    public AccountActivatedEvent(String id, AccountStatus accountStatus) {
        super(id);
        this.accountStatus = accountStatus;
    }

}


```

```java
package ma.ouadouch.Event_Driven_Architecture.common_api.events;

import lombok.Getter;

public class AccountCreatedEvent extends BaseEvent<String> {
    @Getter
    private double initialBalance;
    @Getter
    private String currency;

    public AccountCreatedEvent(String id, double initialBalance, String currency) {
        super(id);
        this.initialBalance = initialBalance;
        this.currency = currency;
    }
}


```

### 3. Créer controller
```java
package ma.ouadouch.Event_Driven_Architecture.commands.controllers;

import ma.ouadouch.Event_Driven_Architecture.common_api.commands.CreateAccountCommand;
import ma.ouadouch.Event_Driven_Architecture.common_api.dtos.CreateAccountRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping(path = "/commands/account")
public class AccountCommandController {
    private CommandGateway commandGateway;
    private EventStore eventStore;

    @PostMapping("/create")
    public CompletableFuture<String> createAccount(@RequestBody CreateAccountRequestDTO request) {
        CompletableFuture<String> response = commandGateway.send(
                new CreateAccountCommand(UUID.randomUUID().toString(), request.getInitialBalance(),
                        request.getCurrency()));
        return response;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception ex) {
        return ResponseEntity.internalServerError().body(ex.getMessage());
    }

    @GetMapping("/eventStore/{accountId}")
    public Stream eventStore(@PathVariable String accountId) {
        return eventStore.readEvents(accountId).asStream();
    }
}

```

