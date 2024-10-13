package Models;

public enum STATUS_PEDIDO {
    Em_Preparacao,
    Procurando_Entregador,
    A_Caminho,
    Entregue,
    Cancelado;

    public static String formatarParaString(STATUS_PEDIDO status) {
        return status.name().replace("_", " ");
    }
}
