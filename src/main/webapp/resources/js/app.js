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

      // Bag summary description
      let bagsNum = document.querySelector("#quantity").value;
      let bagsDescription;
      if (bagsNum == 1) {
        bagsDescription = bagsNum + " worek zawierający ";
      } else if (bagsNum > 1 && bagsNum < 5) {
        bagsDescription = bagsNum + " worki zawierające ";
      } else if (bagsNum > 4) {
        bagsDescription = bagsNum + " worków zawierających ";
      } else {
        bagsDescription = "0 worków zawierających ";
      }

      let checkedCategories = document.querySelectorAll("input[name='categories']:checked ~ span.description");

      for (let i = 0; i < checkedCategories.length; i++) {
        let separator = ", ";
        if (i === checkedCategories.length - 2) {
          separator = " i ";
        } else if (i === checkedCategories.length - 1) {
          separator = "";
        }
        debugger;
        console.log("test")
        bagsDescription += checkedCategories[i].innerText + separator;
      }

      // Institution summary description
      let inst = document.querySelector("input[name='institution']:checked ~ span.description > div.title")
      let instDescription = "Nie wybrano fundacji";
      if (inst !== null) {
        instDescription = `${inst.innerText.replaceAll("Fundacja", "Dla fundacji")}`;
      }

      // Contact summary

      // Summary
      document.querySelector("#bagsSummary").innerText = bagsDescription;
      document.querySelector("#institutionSummary").innerText = instDescription;
      document.querySelector("li#street").innerText = document.querySelector("#street").value;
      document.querySelector("li#city").innerText = document.querySelector("#city").value;
      document.querySelector("li#zipCode").innerText = document.querySelector("#zipCode").value;
      document.querySelector("li#phone").innerText = document.querySelector("#phoneNumber").value;
      document.querySelector("li#date").innerText = document.querySelector("#pickUpDate").value;
      document.querySelector("li#time").innerText = document.querySelector("#pickUpTime").value;
      let comment = document.querySelector("#pickUpComment").value;
      if (comment.trim().length === 0) {
        document.querySelector("li#comment").innerText = "Brak uwag";
      } else {
        document.querySelector("li#comment").innerText = "Uwagi: " + document.querySelector("#pickUpComment").value;
      }

    }

  }
  const form = document.querySelector(".form--steps");
  if (form !== null) {
    new FormSteps(form);
  }
});
