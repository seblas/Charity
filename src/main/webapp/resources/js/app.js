document.addEventListener("DOMContentLoaded", function() {

  /**
   * Form Select
   */
  class FormSelect {
    constructor($el) {
      this.$el = $el;
      this.options = [...$el.children];
      this.init();
    }

    init() {
      this.createElements();
      this.addEvents();
      this.$el.parentElement.removeChild(this.$el);
    }

    createElements() {
      // Input for value
      this.valueInput = document.createElement("input");
      this.valueInput.type = "text";
      this.valueInput.name = this.$el.name;

      // Dropdown container
      this.dropdown = document.createElement("div");
      this.dropdown.classList.add("dropdown");

      // List container
      this.ul = document.createElement("ul");

      // All list options
      this.options.forEach((el, i) => {
        const li = document.createElement("li");
        li.dataset.value = el.value;
        li.innerText = el.innerText;

        if (i === 0) {
          // First clickable option
          this.current = document.createElement("div");
          this.current.innerText = el.innerText;
          this.dropdown.appendChild(this.current);
          this.valueInput.value = el.value;
          li.classList.add("selected");
        }

        this.ul.appendChild(li);
      });

      this.dropdown.appendChild(this.ul);
      this.dropdown.appendChild(this.valueInput);
      this.$el.parentElement.appendChild(this.dropdown);
    }

    addEvents() {
      this.dropdown.addEventListener("click", e => {
        const target = e.target;
        this.dropdown.classList.toggle("selecting");

        // Save new value only when clicked on li
        if (target.tagName === "LI") {
          this.valueInput.value = target.dataset.value;
          this.current.innerText = target.innerText;
        }
      });
    }
  }
  document.querySelectorAll(".form-group--dropdown select").forEach(el => {
    new FormSelect(el);
  });

  /**
   * Hide elements when clicked on document
   */
  document.addEventListener("click", function(e) {
    const target = e.target;
    const tagName = target.tagName;

    if (target.classList.contains("dropdown")) return false;

    if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
      return false;
    }

    if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
      return false;
    }

    document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
      el.classList.remove("selecting");
    });
  });

  /**
   * Switching between form steps
   */
  class FormSteps {
    constructor(form) {
      this.$form = form;
      this.$next = form.querySelectorAll(".next-step");
      this.$prev = form.querySelectorAll(".prev-step");
      this.$step = form.querySelector(".form--steps-counter span");
      this.currentStep = 1;

      this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
      const $stepForms = form.querySelectorAll("form > div");
      this.slides = [...this.$stepInstructions, ...$stepForms];

      this.init();
    }

    /**
     * Init all methods
     */
    init() {
      this.events();
      this.updateForm();
    }

    /**
     * All events that are happening in form
     */
    events() {
      // Next step
      this.$next.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep++;
          this.updateForm();
        });
      });

      // Previous step
      this.$prev.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep--;
          this.updateForm();
        });
      });

      // Form submit
      this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
    }

    /**
     * Update form front-end
     * Show next or previous section etc.
     */
    updateForm() {
      this.$step.innerText = this.currentStep;

      // TODO: Validation

      this.slides.forEach(slide => {
        slide.classList.remove("active");

        if (slide.dataset.step == this.currentStep) {
          slide.classList.add("active");
        }
      });

      this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
      this.$step.parentElement.hidden = this.currentStep >= 5;

      // TODO: get data from inputs and show them in summary
      if (this.currentStep === 5) {
        const quantity = document.getElementById("quantity").value;
        let bagsText = getWorekText(quantity) + " (";
        const selectedCategoryDescriptions = document.querySelectorAll
        ('input[name="categories"][type="checkbox"]:checked ~ .description');
        selectedCategoryDescriptions.forEach(description => bagsText += description.textContent + ", ");
        bagsText = bagsText.slice(0,-2);
        bagsText += ")";
        const bagsSummaryText = document.querySelector(".icon-bag").nextElementSibling;
        bagsSummaryText.textContent = bagsText;

        const selectedInstitutionDescription = document.querySelector
        ('input[type="radio"][name="institution.id"]:checked ~ .description .title');
        const institutionSummaryText = document.querySelector(".icon-hand").nextElementSibling;
        institutionSummaryText.textContent = selectedInstitutionDescription.textContent;

        const street = document.getElementById("street").value;
        const city = document.getElementById("city").value;
        const zipCode = document.getElementById("zipCode").value;
        const phoneNumber = document.getElementById("phoneNumber").value;
        const pickUpDate = document.getElementById("pickUpDate").value;
        const pickUpTime = document.getElementById("pickUpTime").value;
        const pickUpComment = document.getElementById("pickUpComment").value;
        const addressColumn = document.getElementById("addressColumn");
        const pickUpColumn = document.getElementById("pickUpColumn");
        addressColumn.innerHTML = "<li>" + street + "</li>"
        + "<li>" + city + "</li>"
            + "<li>" + zipCode + "</li>"
            + "<li>" + phoneNumber + "</li>";

        pickUpColumn.innerHTML = "<li>" + pickUpDate + "</li>"
            + "<li>" + pickUpTime + "</li>"
            + "<li>" + pickUpComment + "</li>";

      }

      function getWorekText(count) {
        let tekst;
        switch (true) {
          case (count === 1):
            tekst = `${count} worek`;
            break;
          case (count >= 2 && count <= 4):
            tekst = `${count} worki`;
            break;
          case (count >= 5 && count <= 21):
            tekst = `${count} worków`;
            break;
          default:
            let remainder = count % 10;
            let remainder100 = count % 100;
            if (remainder === 1 && remainder100 !== 11) {
              tekst = `${count} worek`;
            } else if (remainder >= 2 && remainder <= 4 && !(remainder100 >= 12 && remainder100 <= 14)) {
              tekst = `${count} worki`;
            } else {
              tekst = `${count} worków`;
            }
            break;
        }
        return tekst;
      }










    }

  }
  const form = document.querySelector(".form--steps");
  if (form !== null) {
    new FormSteps(form);
  }
});
