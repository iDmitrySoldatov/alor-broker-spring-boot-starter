package algo.trading.starter.client.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/** Instrument type. */
@Getter
@RequiredArgsConstructor
public enum InstrumentType {
  FOR("Валюта"),
  CS("Обыкновенные акции компании"),
  PS("Привилегированные акции компании"),
  MF("Паевой инвестиционный фонд"),
  RDR("Российская депозитарная расписка"),
  EUSOV("Облигация федерального займа"),
  MUNI("Муниципальная облигация"),
  CORP("Корпоративная облигация");

  private final String description;
}
