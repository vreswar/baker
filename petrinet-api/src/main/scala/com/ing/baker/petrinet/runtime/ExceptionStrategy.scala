package com.ing.baker.petrinet.runtime

import com.ing.baker.petrinet.api.Marking

object ExceptionStrategy {

  /**
   * Indicates a non recoverable exception that should prevent any execution of this and other transitions.
   */
  case object Fatal extends ExceptionStrategy

  /**
   * Indicates that this transition should not be retried but other transitions in the petri net still can.
   */
  case object BlockTransition extends ExceptionStrategy

  /**
   * Retries firing the transition after some delay.
   */
  case class RetryWithDelay(delay: Long) extends ExceptionStrategy {
    require(delay > 0, "Delay must be greater then zero")
  }

  case class Continue[P[_], E](marking: Marking[P], event: E) extends ExceptionStrategy {}
}

sealed trait ExceptionStrategy