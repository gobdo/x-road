/*
 * Supports one open dialog at a time.
 */
(function(XROAD_URL_AND_CERT_DIALOG, $, undefined) {
    var certOptional = false;
    var hasCert = false;
    var edit = false;

    var tempCertId;
    var params;

    function enableActions(prefix) {
        certViewButton = $("#" + prefix + "_cert_view");
        certViewButton.hide();

        if (hasCert) {
            certViewButton.show();
        }
    }

    function notifyInputChanged(prefix) {
        // We trigger URL change, as it does not cause side-effects.
        $("#" + prefix + "_url").change();
    }

    // FUTURE: Can we replace it with enableForInput() if latter works properly?
    function updateSubmitButton(prefix, ev) {
        var submitButton = $("#" + prefix + "_url_and_cert_submit");
        var urlField = $("#" + prefix + "_url");
        var certField = $("#" + prefix + "_cert");

        if (!isInputFilled(urlField, ev)) {
            submitButton.disable();
            return;
        }

        if (hasCert || certOptional) {
            submitButton.enable();
            return;
        }

        isInputFilled(certField) ?
                submitButton.enable() : submitButton.disable();
    }

    XROAD_URL_AND_CERT_DIALOG.initForPrefix =
            function(prefix, onAdd, onEdit, onCertView) {

        $("#" + prefix + "_url_and_cert_dialog").initDialog({
            autoOpen: false,
            modal: true,
            height: 250,
            width: 800,
            buttons: [
                { text: _("common.ok"),
                  id: prefix + "_url_and_cert_submit",
                  disabled: "disabled",
                  click: function() {
                      params.url = $("#" + prefix + "_url").val();
                      params.temp_cert_id = tempCertId;

                      edit ? onEdit(params) : onAdd(params);
                  }
                },
                { text: _("common.cancel"),
                  click: function() {
                      $(this).dialog("close");
                  }
                }
            ],
            open: function() {
                $("#" + prefix + "_url_and_cert_submit").disable();
            },
        });

        $(document).on("change", "#" + prefix + "_cert", function() {
            $(this).closest("form").submit();
        });

        $("#" + prefix + "_url").on("change keyup paste", function(ev) {
            enableActions(prefix);
            updateSubmitButton(prefix, ev);
        });

        $("#" + prefix + "_cert_view").click(function() {
            onCertView(tempCertId != null ? {temp_cert_id: tempCertId} : params);
            return false;
        });
    }

    XROAD_URL_AND_CERT_DIALOG.openEditDialog = function(
            prefix, title, _certOptional, _url, _hasCert, _params) {
        certOptional = _certOptional;
        hasCert = _hasCert;
        edit = true;
        tempCertId = null;
        params = _params;

        $("#" + prefix + "_url").val(_url);
        $("#" + prefix + "_cert_file").text("");

        certUploadButton = $("#" + prefix + "_cert_button");
        certUploadButton.hide();

        if (certOptional) {
            certUploadButton.show();
        }

        $("#" + prefix + "_url_and_cert_dialog").dialog(
            "option", "title", title);

        enableActions(prefix);

        $("#" + prefix + "_url_and_cert_dialog").dialog("open")
    };

    XROAD_URL_AND_CERT_DIALOG.openAddDialog = function(
            prefix, title, _certOptional, _params) {
        certOptional = _certOptional;
        hasCert = false;
        edit = false;
        tempCertId = null;
        params = _params;

        $("#" + prefix + "_url").val("");
        $("#" + prefix + "_cert_file").text("");
        $("#" + prefix + "_cert_button").show();

        $("#" + prefix + "_url_and_cert_dialog").dialog(
            "option", "title", title);

        enableActions(prefix);

        $("#" + prefix + "_url_and_cert_dialog").dialog("open")
    };

    XROAD_URL_AND_CERT_DIALOG.closeDialog = function(prefix) {
        $("#" + prefix + "_url_and_cert_dialog").dialog("close");
    }

    XROAD_URL_AND_CERT_DIALOG.certUploadCallback = function(response) {
        var prefix = response.data.prefix;

        if (response.success) {
            tempCertId = response.data.temp_cert_id;
            hasCert = true;

            $("#" + prefix + "_cert_file").text(
                $("#" + prefix + "_cert").val());

            notifyInputChanged(prefix);
        } else {
            tempCertId = null;
            hasCert = false;

            $("#" + prefix + "_cert_file").text("");
        }

        enableActions(prefix);
        showMessages(response.messages)
    };

}(window.XROAD_URL_AND_CERT_DIALOG = window.XROAD_URL_AND_CERT_DIALOG || {}, jQuery));
