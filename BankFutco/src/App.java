import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

import model.Account;
import model.Loans;

import services.AccountService;
import services.LoanService;


public class App {
    private static AccountService accountService=new AccountService();
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
                        runCrudMenu(sc, "Account");
                        break;
                    case "2":
                        runCrudMenu(sc, "Balance");
                        break;
                    case "3":
                        runCrudMenuLoans(sc);
                        break;

                    case "4":
                        runCrudMenu(sc, "Cards");
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

    private static void runCrudMenu(Scanner sc, String entityName) {
        boolean back = false;
        while (!back) {
            printCrudMenu(entityName);
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1":
                    System.out.println("[" + entityName + "] Crear - placeholder (pedir datos e invocar servicio)");
                    //Deben tomar los datos por consola, usar Scanner
                    Account account = new Account("ACC010", "Johanny Valencia", "johanny.valencia@example.com", "3000000001", "Savings", "Calle 20 de Turbaco-Bolivar"); 
                    accountService.save(account); 
                    break;
                case "2":
                    System.out.print("[" + entityName + "] Leer por id - ingrese id: ");
                    String id = sc.nextLine().trim();
                    System.out.println("Buscar " + entityName + " con id=" + id + " - placeholder");
                    accountService.findById(id).ifPresentOrElse(
                        acc -> System.out.println("Encontrado: " + acc),
                        () -> System.out.println(entityName + " con id=" + id + " no encontrado.")
                    );
                    break;
                case "3":
                    System.out.println("[" + entityName + "] Listar todos - placeholder");
                    accountService.findAll().stream().forEach(System.out::println);
                    break;
                case "4":
                    System.out.print("[" + entityName + "] Actualizar - ingrese id: ");
                    String idUp = sc.nextLine().trim();
                    System.out.println("Actualizar " + entityName + " id=" + idUp + " - placeholder");
                    //Deben tomar los datos por consola, usar Scanner
                    Account updateAccount = new Account("ACC010", "Johanny Valencia", "johanny.valencia@example.com", "3000000001", "Savings", "Calle 20 de Turbaco-Bolivar"); 
                    accountService.save(updateAccount);
                    break;
                case "5":
                    System.out.print("[" + entityName + "] Eliminar - ingrese id: ");
                    String idDel = sc.nextLine().trim();
                    System.out.println("Eliminar " + entityName + " id=" + idDel + " - placeholder");
                    accountService.deleteById(idDel);
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static void printCrudMenu(String entityName) {
        System.out.println("\n--- " + entityName + " CRUD ---");
        System.out.println("1. Create");
        System.out.println("2. Read by id");
        System.out.println("3. List all");
        System.out.println("4. Update");
        System.out.println("5. Delete");
        System.out.println("0. Back");
        System.out.print("Seleccione una opción: ");
    }




    private static LoanService loanService = new LoanService();

private static void runCrudMenuLoans(Scanner sc) {
    boolean back = false;

    while (!back) {
        printCrudMenuLoans("Loans");
        String opt = sc.nextLine().trim();

        switch (opt) {
            case "1": // Create New Loan
                try {
                    System.out.println("\n=== Crear nuevo préstamo ===");
                    System.out.print("Tipo de préstamo: ");
                    String type = sc.nextLine().trim();

                    System.out.print("Monto total del préstamo: ");
                    BigDecimal totalLoan = new BigDecimal(sc.nextLine().trim());

                    System.out.print("Monto pagado hasta ahora: ");
                    BigDecimal amountPaid = new BigDecimal(sc.nextLine().trim());

                    BigDecimal outstandingAmt = totalLoan.subtract(amountPaid);
                    LocalDate date = LocalDate.now();

                    Loans newLoan = new Loans(date, type, totalLoan, amountPaid, outstandingAmt);
                    loanService.save(newLoan);

                    System.out.println("Préstamo creado exitosamente.");
                } catch (Exception e) {
                    System.out.println("Error al crear el préstamo: " + e.getMessage());
                }
                break;

            case "2": // Read by ID
                System.out.print("Ingrese la fecha del préstamo (formato yyyy-MM-dd): ");
                try {
                    LocalDate searchDate = LocalDate.parse(sc.nextLine().trim());
                    String id = searchDate.toString();

                    loanService.findById(id).ifPresentOrElse(
                        loan -> System.out.println("Préstamo encontrado: " + loan),
                        () -> System.out.println("No se encontró ningún préstamo con esa fecha.")
                    );
                } catch (Exception e) {
                    System.out.println("Fecha inválida. Use el formato correcto (yyyy-MM-dd).");
                }
                break;

            case "3": // List all Loans
                System.out.println("\n=== Lista de préstamos ===");
                var loans = loanService.findAll();
                if (loans.isEmpty()) {
                    System.out.println("No hay préstamos registrados.");
                } else {
                    loans.forEach(System.out::println);
                }
                break;

            case "4": // Abonar al crédito
                System.out.print("Ingrese la fecha del préstamo (formato yyyy-MM-dd): ");
                try {
                    LocalDate dateAbono = LocalDate.parse(sc.nextLine().trim());
                    String id = dateAbono.toString();

                    var optionalLoan = loanService.findById(id);
                    if (optionalLoan.isPresent()) {
                        Loans loan = optionalLoan.get();

                        System.out.println("Préstamo encontrado: " + loan);
                        System.out.print("Ingrese el monto a abonar: ");
                        BigDecimal abono = new BigDecimal(sc.nextLine().trim());

                        BigDecimal nuevoPagado = loan.getAmountPaid().add(abono);
                        BigDecimal nuevoPendiente = loan.getTotalLoan().subtract(nuevoPagado);

                        // Actualiza los valores
                        loan.setAmountPaid(nuevoPagado);
                        loan.setOutstandingAmt(nuevoPendiente);

                        loanService.save(loan);

                        System.out.println("Abono realizado correctamente.");
                        System.out.println("Nuevo monto pagado: " + nuevoPagado);
                        System.out.println("Saldo pendiente: " + nuevoPendiente);
                    } else {
                        System.out.println("No se encontró un préstamo con esa fecha.");
                    }
                } catch (Exception e) {
                    System.out.println("Error al procesar el abono: " + e.getMessage());
                }
                break;

            case "0":
                back = true;
                break;

            default:
                System.out.println("Opción no válida. Intente de nuevo.");
        }
    }
}


private static void printCrudMenuLoans(String entityName) {
    System.out.println("\n--- " + entityName + " ---");
    System.out.println("1. Create New Loan");
    System.out.println("2. Read by ID");
    System.out.println("3. List all Loans");
    System.out.println("4. Pay Towards the Loans");
    System.out.println("0. Back");
    System.out.print("Seleccione una opción: ");
}

}
