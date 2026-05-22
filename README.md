# Snakes and Ladders — Software Design and Architecture Coursework

## Overview
This project is a Java console simulation of Snakes and Ladders built using Spring Boot, Clean Architecture, and the Strategy design pattern. It supports two and four player games across two board sizes with configurable end rules, hit rules, and wormhole teleportation.

## How to Run
1. Clone the repository
2. Open in IntelliJ IDEA and allow Maven to load `pom.xml`
3. Run `GameApplication.java` — Spring Boot starts and calls `GameRunner.runAll()`
4. All seven game configurations print to the console automatically

## Clean Architecture — Ports and Adapters
The project is structured into three layers. Dependencies only flow inward — infrastructure depends on usecase and domain, usecase depends only on domain, and domain has no dependencies at all.

## Project Structure

```mermaid
graph TD
    subgraph Domain
        A[Player interface]
        B[Board]
        C[Dice]
        D[DiceShaker interface]
        E[EndRuleStrategy interface]
        F[HitRuleStrategy interface]
        G[TeleportRuleStrategy interface]
    end
    subgraph Usecase
        H[Game]
        I[GameOutputPort interface]
    end
    subgraph Infrastructure
        J[BasePlayer]
        K[ConsoleGameOutput]
        L[FixedDiceShaker]
        M[RandomDiceShaker]
        N[OvershootEndRule]
        O[BounceBackEndRule]
        P[IgnoreHitRule]
        Q[StayHitRule]
        R[NoWormholeRule]
        S[WormholeRule]
        T[GameRunner]
    end
    H --> A
    H --> I
    H --> E
    H --> F
    H --> G
    J --> A
    K --> I
    L --> D
    M --> D
    N --> E
    O --> E
    P --> F
    Q --> F
    R --> G
    S --> G