import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import model.Account;
import model.Balance;
import model.Cards;
import model.Loans;
import services.AccountService;
import services.BalanceService;
import services.CardService;
import services.LoansService;

public class App {
    private static AccountService accountService = new AccountService();
    private static BalanceService balanceService = new BalanceService();
    private static LoansService loansService = new LoansService();
    private static CardService cardService = new CardService();
    public static void main(String[] args) throws Exception {
        /*accountService.findAll().stream().forEach(a->System.out.println(a));
        Account account = new Account("ACC010", "Johanny Valencia", "johanny.valencia@example.com", "3000000001",
                "Savings", "Calle 20 de Turbaco-Bolivar");
        accountService.save(account);
        System.out.println("*".repeat(100));
        accountService.findAll().stream().forEach(a->System.out.println(a));
        System.out.println("-".repeat(100));
        accountService.findById("ACC009").ifPresentOrElse(
                acc -> System.out.println("Encontrado: " + acc),
                () -> System.out.println(" account number no encontrado."));
        accountService.deleteById("ACC010");
        System.out.println("/".repeat(100));
        accountService.findAll().stream().forEach(System.out::println);*/

        try (Scanner sc = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                printMainMenu();
                String option = sc.nextLine().trim();
                switch (option) {
                    case "1":
                        runCrudMenuAccount(sc, "Account");
                        break;
                    case "2":
                        runCrudMenuBalance(sc, "Balance");
                        break;
                    case "3":
                        runCrudMenuLoans(sc, "Loans");
                        break;
                    case "4":
                        runCrudMenuCards(sc, "Cards");
                        break;
                    case "0":
                        running = false;
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            }
        }

    }

        private static void printMainMenu() {
        System.out.println("\n=== Menú Principal ===");
        System.out.println("1. Account");
        System.out.println("2. Balance");
        System.out.println("3. Loans");
        System.out.println("4. Cards");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void runCrudMenuAccount(Scanner sc, String entityName) {
        boolean back = false;
        int number = 10;
        while (!back) {
            printCrudMenuAccount(entityName);
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1":
                    System.out.println("[" + entityName + "] Crear - placeholder");
                    number++;
                    String numStr = Integer.toString(number);
                    String accountNumber = "ACC0" + numStr;
                    System.out.print("Ingrese el nombre: ");
                    String name = sc.nextLine().trim();
                    System.out.print("Ingrese el email: ");
                    String email = sc.nextLine().trim();
                    System.out.print("Ingrese el telefono: ");
                    String mobileNumber = sc.nextLine().trim();
                    System.out.print("Ingrese el tipo de cuenta(Savings o Checking): ");
                    String accountType = sc.nextLine().trim();
                    System.out.print("Ingrese la direccion: ");
                    String address = sc.nextLine().trim();
                    Account account = new Account(accountNumber, name, email, mobileNumber, accountType, address); 
                    accountService.save(account);
                    balanceService.createnewBalance(accountNumber);
                    System.out.println("cuenta creada con exito. Numero de cuenta: " + accountNumber);
                    break;
                case "2":
                    System.out.print("[" + entityName + "] Leer por id - ingrese id: ");
                    String id = sc.nextLine().trim();
                    System.out.println("Buscar " + entityName + " con id=" + id + " - placeholder");
                    accountService.findById(id).ifPresentOrElse(
                        acc -> System.out.println("Encontrado: " + accountService.findById(id).toString() + "Balance: " + balanceService.findByAccount(id).toString()),
                        () -> System.out.println(entityName + " con id: " + id + " no encontrado.")
                    );
                    break;
                case "3":
                    System.out.println("[" + entityName + "] Listar todos - placeholder");
                    //accountService.findAll().stream().forEach(System.out::println);
                    accountService.findAll().stream().forEach(acc -> System.out.println("Cuenta: "+ accountService.findById(acc.getAccountNumber()).toString() +"Balance: " + balanceService.findByAccount(acc.getAccountNumber()).toString()));
                    break;
                case "4":
                    System.out.print("[" + entityName + "] Actualizar - ingrese id: ");
                    String idUp = sc.nextLine().trim();
                    accountService.findById(idUp).ifPresentOrElse(
                        acc -> {
                        //System.out.println("Actualizar " + entityName + " id: " + idUp + " - placeholder");
                        System.out.print("Ingrese el nombre: ");
                        String nameU = sc.nextLine().trim();
                        System.out.print("Ingrese el email: ");
                        String emailU = sc.nextLine().trim();
                        System.out.print("Ingrese el telefono: ");
                        String mobileNumberU = sc.nextLine().trim();
                        System.out.print("Ingrese el tipo de cuenta(Savings o Checking): ");
                        String accountTypeU = sc.nextLine().trim();
                        System.out.print("Ingrese la direccion: ");
                        String addressU = sc.nextLine().trim();
                        Account updateAccount = new Account(idUp, nameU, emailU, mobileNumberU, accountTypeU, addressU); 
                        accountService.save(updateAccount);
                        System.out.println("cuenta actualizada con exito.");
                    },
                        () -> System.out.println(entityName + " con id: " + idUp + " no encontrado.")
                    );
                    break;
                case "5":
                    System.out.print("[" + entityName + "] Eliminar - ingrese id: ");
                    String idDel = sc.nextLine().trim();
                    System.out.println("Eliminar " + entityName + " id: " + idDel + " - placeholder");
                    accountService.deleteById(idDel);
                    balanceService.deleteById(idDel);
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

   
    private static void runCrudMenuBalance(Scanner sc, String entityName) {
    boolean back = false;
    while (!back) {
        printCrudMenuBalance(entityName);
        String opt = sc.nextLine().trim();
        switch (opt) {
            case "1":
                System.out.println("[" + entityName + "] Agregar fondos");
                System.out.print("Ingrese el ID de la cuenta: ");
                String idAcc = sc.nextLine().trim().toUpperCase();

                accountService.findById(idAcc).ifPresentOrElse(
                    acc -> {
                        System.out.println("Cuenta encontrada: " + acc.getAccountNumber() + " - " + acc.getName());
                        System.out.print("Ingrese el monto a depositar: ");
                        BigDecimal amount = sc.nextBigDecimal();
                        sc.nextLine();

                        Balance nuevoBalance = new Balance(
                            acc.getAccountNumber(),
                            amount,               
                            BigDecimal.ZERO,  
                            amount.add(
                                balanceService.calculateCurrentBalance(acc.getAccountNumber()).orElse(BigDecimal.ZERO)
                            ),
                            LocalDate.now(),
                            "Depósito realizado por el usuario"
                        );

                        balanceService.save(nuevoBalance);

                        System.out.println("Deposito exitoso. Nuevo saldo: " + nuevoBalance.getClosingBalance());
                    },
                    () -> System.out.println("Error al depositar fondos: " + entityName + " con ID: " + idAcc + " no encontrado.")
                );
                break;
            case "2":
                System.out.println("[" + entityName + "] Retirar fondos");
                System.out.print("Ingrese el ID de la cuenta: ");
                String idAcc2 = sc.nextLine().trim().toUpperCase();

                accountService.findById(idAcc2).ifPresentOrElse(
                    acc -> {
                        System.out.println("Cuenta encontrada: " + acc.getAccountNumber() + " - " + acc.getName());

                        System.out.print("Ingrese el monto a retirar: ");
                        BigDecimal amount = sc.nextBigDecimal();
                        sc.nextLine();
                        BigDecimal currentBalance = balanceService.calculateCurrentBalance(acc.getAccountNumber())
                                .orElse(BigDecimal.ZERO);

                        if (currentBalance.compareTo(amount) < 0) {
                            System.out.println("Fondos insuficientes. Saldo actual: " + currentBalance);
                            return;
                        }
                        Balance nuevoBalance = new Balance(
                            acc.getAccountNumber(),
                            BigDecimal.ZERO,         
                            amount,                 
                            currentBalance.subtract(amount),
                            LocalDate.now(),
                            "Retiro de efectivo"
                        );

                        balanceService.save(nuevoBalance);

                        System.out.println("Retiro exitoso. Nuevo saldo: " + nuevoBalance.getClosingBalance());
                    },
                    () -> System.out.println("Error: " + entityName + " con ID: " + idAcc2 + " no encontrado.")
                );
                break;
            case "3":
                    System.out.println("[" + entityName + "] Listar todas las transacciones");
                    System.out.print("Ingrese el ID de la cuenta: ");
                    String idAcc3 = sc.nextLine().trim().toUpperCase();

                    accountService.findById(idAcc3).ifPresentOrElse(
                        acc -> {
                            System.out.println("\nTransacciones de la cuenta: " + acc.getAccountNumber() + " - " + acc.getName());
                            List<Balance> transacciones = balanceService.findByAccount(acc.getAccountNumber());

                            if (transacciones.isEmpty()) {
                                System.out.println("No hay transacciones registradas para esta cuenta.");
                            } else {
                                for (Balance b : transacciones) {
                                    System.out.println("-------------------------------------------");
                                    System.out.println("Fecha: " + b.getDate());
                                    System.out.println("Descripción: " + b.getDescription());
                                    System.out.println("Entrada (Cash In): " + b.getCashIn());
                                    System.out.println("Salida (Cash Out): " + b.getCashOut());
                                    System.out.println("Balance final: " + b.getClosingBalance());
                                }
                                System.out.println("-------------------------------------------");
                            }
                        },
                        () -> System.out.println("Cuenta con ID " + idAcc3 + " no encontrada.")
                    );
                    break;
            case "4":
                System.out.println("[" + entityName + "] Ver saldo actual");
                System.out.print("Ingrese el ID de la cuenta: ");
                String idAcc4 = sc.nextLine().trim().toUpperCase();

                accountService.findById(idAcc4).ifPresentOrElse(
                    acc -> {
                        System.out.println("\nCuenta encontrada: " + acc.getAccountNumber() + " - " + acc.getName());

                        BigDecimal saldoActual = balanceService.calculateCurrentBalance(acc.getAccountNumber())
                                .orElse(BigDecimal.ZERO);

                        System.out.println("Saldo actual: " + saldoActual);
                    },
                    () -> System.out.println("Cuenta con ID " + idAcc4 + " no encontrada.")
                );
                break;
            case "0":
                back = true;
                break;
            default:
                System.out.println("Opción no válida. Intente de nuevo.");
        }
    }
}

private static void runCrudMenuLoans(Scanner sc, String entityName) {
    boolean back = false;
    while (!back) {
        printCrudMenuLoans(entityName); 
        String opt = sc.nextLine().trim();

        switch (opt) {
            case "1": 
                System.out.print("Ingrese el accountNumber de la cuenta: ");
                String accNumberCreate = sc.nextLine().trim().toUpperCase();

                accountService.findById(accNumberCreate).ifPresentOrElse(
                    acc -> {
                        System.out.print("Ingrese el tipo de préstamo (Hipotecario, Personal, Educativo, Vehicular): ");
                        String type = sc.nextLine().trim();
                        System.out.print("Ingrese el monto total del préstamo: ");
                        BigDecimal totalLoan = sc.nextBigDecimal();
                        sc.nextLine(); 

                        loansService.createLoan(acc.getAccountNumber(), type, totalLoan);
                        System.out.println("Préstamo creado exitosamente para la cuenta " + acc.getAccountNumber());
                    },
                    () -> System.out.println("Cuenta con ID " + accNumberCreate + " no encontrada.")
                );
                break;

            case "2": 
                System.out.print("Ingrese el ID del préstamo (solo accountNumber): ");
                String loanId = sc.nextLine().trim().toUpperCase();

                loansService.findById(loanId).ifPresentOrElse(
                    loan -> System.out.println("Préstamo encontrado: " + loan),
                    () -> System.out.println("No se encontró préstamo con ID " + loanId)
                );
                break;

            case "3": 
                System.out.print("Ingrese el accountNumber de la cuenta: ");
                String accNumberList = sc.nextLine().trim().toUpperCase();

                List<Loans> loansList = loansService.findByAccountNumber(accNumberList);
                if (loansList.isEmpty()) {
                    System.out.println("No hay préstamos registrados para la cuenta " + accNumberList);
                } else {
                    System.out.println("Préstamos de la cuenta " + accNumberList + ":");
                    for (Loans loan : loansList) {
                        System.out.println(loan);
                    }
                }
                break;

            case "4": 
                System.out.print("Ingrese el accountNumber de la cuenta: ");
                String accNumberPayment = sc.nextLine().trim().toUpperCase();

                System.out.print("Ingrese el monto del pago: ");
                BigDecimal paymentAmount = sc.nextBigDecimal();
                sc.nextLine(); 

                loansService.makePayment(accNumberPayment, paymentAmount)
                        .ifPresentOrElse(
                            loan -> System.out.println("Pago registrado exitosamente. Deuda restante: " + loan.getOutstandingAmt()),
                            () -> System.out.println("No se pudo registrar el pago para la cuenta " + accNumberPayment + ". Verifique que haya saldo suficiente o que exista un préstamo.")
                        );
                break;

            case "5": 
                System.out.print("Ingrese el accountNumber de la cuenta: ");
                String accNumberDebt = sc.nextLine().trim().toUpperCase();

                loansService.calculateOutstanding(accNumberDebt)
                        .ifPresentOrElse(
                            debt -> System.out.println("Deuda actual de la cuenta " + accNumberDebt + ": " + debt),
                            () -> System.out.println("No se encontró deuda para la cuenta " + accNumberDebt)
                        );
                break;

            case "0": 
                back = true;
                break;

            default:
                System.out.println("Opción no válida. Intente de nuevo.");
        }
    }
}


private static void runCrudMenuCards(Scanner sc, String entityName) {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- " + entityName + " CRUD ---");
            System.out.println("1. Crear tarjeta");
            System.out.println("2. Consultar por ID");
            System.out.println("3. Listar todas");
            System.out.println("4. Realizar consumo");
            System.out.println("5. Abonar a la tarjeta");
            System.out.println("6. Eliminar tarjeta");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            String opt = sc.nextLine().trim();

            switch (opt) {
                case "1":
                    System.out.print("Ingrese número de cuenta para asignar tarjeta: ");
                    String accNumber = sc.nextLine().trim().toUpperCase();
                    if (accountService.findById(accNumber).isEmpty()) {
                        System.out.println("Cuenta no encontrada.");
                        break;
                    }
                    String cardNumber = "CARD" + accNumber.substring(3);
                    Cards newCard = new Cards(cardNumber, "Debit", new BigDecimal("1000000"), BigDecimal.ZERO,
                            accNumber);
                    cardService.save(newCard);
                    System.out.println("Tarjeta creada y asignada correctamente a " + accNumber);
                    break;

                case "2":
                    System.out.print("Ingrese número de tarjeta: ");
                    String id = sc.nextLine().trim().toUpperCase();
                    cardService.findById(id).ifPresentOrElse(card -> {
                        System.out.println(card);
                        accountService.findById(card.getAccountNumber())
                                .ifPresent(acc -> System.out.println("Propietario: " + acc.getName()));
                    }, () -> System.out.println("Tarjeta no encontrada."));
                    break;

                case "3":
                    System.out.println("--- Listado de tarjetas ---");
                    cardService.findAll().forEach(card -> {
                        System.out.println(card);
                        accountService.findById(card.getAccountNumber())
                                .ifPresent(acc -> System.out.println("→ Propietario: " + acc.getName()));
                        System.out.println("--------------------------------------");
                    });
                    break;

                case "4":
                    System.out.print("Ingrese número de tarjeta: ");
                    String idC = sc.nextLine().trim().toUpperCase();
                    System.out.print("Ingrese monto del consumo: ");
                    BigDecimal consumo = sc.nextBigDecimal();
                    sc.nextLine();
                    cardService.makeTransaction(idC, consumo, false)
                            .ifPresent(c -> System.out.println("Consumo realizado: " + c));
                    break;

                case "5":
                    System.out.print("Ingrese número de tarjeta: ");
                    String idP = sc.nextLine().trim().toUpperCase();
                    System.out.print("Ingrese monto a abonar: ");
                    BigDecimal abono = sc.nextBigDecimal();
                    sc.nextLine();
                    cardService.makeTransaction(idP, abono, true)
                            .ifPresent(c -> System.out.println("Abono realizado: " + c));
                    break;

                case "6":
                    System.out.print("Ingrese número de tarjeta a eliminar: ");
                    String idDel = sc.nextLine().trim().toUpperCase();
                    if (cardService.deleteById(idDel))
                        System.out.println("Tarjeta eliminada exitosamente.");
                    else
                        System.out.println("No se encontró la tarjeta.");
                    break;

                case "0":
                    back = true;
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }



private static void printCrudMenuAccount(String entityName) {
        System.out.println("\n--- " + entityName + " CRUD ---");
        System.out.println("1. Create");
        System.out.println("2. Read by id");
        System.out.println("3. List all");
        System.out.println("4. Update");
        System.out.println("5. Delete");
        System.out.println("0. Back");
        System.out.print("Seleccione una opción: ");
    }


    private static void printCrudMenuBalance(String entityName) {
        System.out.println("\n--- " + entityName + " Transactions ---");
        System.out.println("1. Add Funds");
        System.out.println("2. Cash out");
        System.out.println("3. List all transactions");
        System.out.println("4. current balance");
        System.out.println("0. Back");
        System.out.print("Seleccione una opción: ");
    }

    private static void printCrudMenuLoans(String entityName) {
    System.out.println("\n--- " + entityName + "---");
    System.out.println("1. Create");
    System.out.println("2. Read by id");
    System.out.println("3. List all");
    System.out.println("4. Make Payment");
    System.out.println("5. Check Outstanding Debt");
    System.out.println("0. Back");
    System.out.print("Seleccione una opción: ");
}

}
