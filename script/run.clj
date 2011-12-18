(use 'ring.adapter.jetty)
(require 'cljblog.core)

(run-jetty #'cljblog.core/app {:port 2323})
