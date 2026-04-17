# Student devcontainer for the Clojure module of the Paradigms Course.
# Provides JDK, Clojure CLI, clj-kondo, and REPL ergonomics — nothing else.

FROM eclipse-temurin:21-jdk-jammy

# --- Pinned versions (must match the authoring environment tests were run against) ---
ARG CLOJURE_VERSION=1.12.0.1530
ARG CLJ_KONDO_VERSION=2024.11.14

# --- System packages ---
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
        curl \
        git \
        less \
        rlwrap \
        unzip && \
    rm -rf /var/lib/apt/lists/*

# --- Clojure CLI ---
# Official installer from https://clojure.org/guides/install_clojure
RUN curl -fsSL "https://download.clojure.org/install/linux-install-${CLOJURE_VERSION}.sh" \
        -o /tmp/clojure-install.sh && \
    chmod +x /tmp/clojure-install.sh && \
    /tmp/clojure-install.sh && \
    rm /tmp/clojure-install.sh

# --- clj-kondo (static linter) ---
RUN curl -fsSL "https://github.com/clj-kondo/clj-kondo/releases/download/v${CLJ_KONDO_VERSION}/clj-kondo-${CLJ_KONDO_VERSION}-linux-amd64.zip" \
        -o /tmp/clj-kondo.zip && \
    unzip -o /tmp/clj-kondo.zip -d /usr/local/bin && \
    rm /tmp/clj-kondo.zip

# --- Non-root user ---
RUN groupadd --gid 1000 student && \
    useradd --uid 1000 --gid 1000 --create-home --shell /bin/bash student && \
    # Let the student install additional tools without sudo
    mkdir -p /home/student/.local/bin && \
    chown -R student:student /home/student

ENV PATH="/home/student/.local/bin:${PATH}"

# Pre-download Clojure core jars so first REPL start isn't slow
RUN su student -c "clojure -e '(println :ready)'"

WORKDIR /workspaces/clojure
USER student

CMD ["bash"]
