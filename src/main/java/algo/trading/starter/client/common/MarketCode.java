package algo.trading.starter.client.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/** Market code. */
@Getter
@RequiredArgsConstructor
public enum MarketCode {
  FORTS("Срочный рынок Московской биржи"),
  FOND("Фондовый рынок Московской биржи"),
  CURR("Валютный рынок Московской биржи"),
  SPBX("Рынок Санкт-Петербургской биржи");

  private final String description;
}
