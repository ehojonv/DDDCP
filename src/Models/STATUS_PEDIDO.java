package Models;

public enum STATUS_PEDIDO {
    EM_PREPARACAO,
    PROCURANDO_ENTREGADOR,
    A_CAMINHO,
    ENTREGUE,
    CANCELADO;

    public static String formatarParaString(STATUS_PEDIDO status) {
        if (status == STATUS_PEDIDO.EM_PREPARACAO) {
            return "Em Preparação";
        }
        return status.name().replace("_", " ");
    }
}
